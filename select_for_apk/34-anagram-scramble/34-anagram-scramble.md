# Anagram / Scramble — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Anagram / Scramble is a single-player word puzzle game in which players are presented with a set of jumbled letters and must reconstruct the correct hidden word (or find as many valid words as possible from those letters in a bonus mode). The core appeal is the satisfying "click" of recognition when you spot the word, combined with time pressure that creates urgency and a streak system that rewards momentum. It targets casual word-game fans from teens through adults, aiming for 2–10 minute sessions.

- **Genre:** Word puzzle / casual
- **Developer:** Original (this blueprint targets a self-built clone)
- **Platforms:** Web (mobile + desktop), optionally wrapped as PWA
- **Release / Last Update:** Original build per this spec (June 2026)
- **Age / Content Rating:** IARC 3+ (all ages; no violence, no mature content) [Estimated]
- **Monetization Model:** Ad-supported free-to-play with optional IAP cosmetics (or purely free for an open-source version) [Estimated]
- **Comparable Titles:** Wordle (NYT), Word Scramble (Arkadium), Word Streak with Friends, Text Twist

---

## 2. Core Loops

- **30-second loop:** A scrambled word appears as shuffled letter tiles → player clicks tiles (or types) to spell the answer → submits → sees correct/wrong feedback → new word.
- **Session loop:** Player selects difficulty → completes a sequence of words (10 per round) against a countdown timer → earns score based on speed and word length → sees round summary with streak, best time, and high score.
- **Meta loop:** Player returns to beat their personal best score per difficulty, climb a local leaderboard, maintain daily streaks, and unlock harder difficulty tiers over multiple sessions.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
- **Unscramble mode (primary):** A single scrambled target word is shown as clickable letter tiles. Player assembles the answer in blank slots. Only the exact target word is accepted.
- **Find-all mode (bonus):** A set of 6–8 letters is shown. Player forms as many valid English words as possible within a time limit. Each valid word scores individually.
- **Shuffle button:** Re-randomizes the displayed tile order (does not reveal the answer); free to use, no penalty.
- **Hint:** Reveals the next correct letter in its correct slot. Costs 50 points per use (or fixed hint count per round — 3 hints per round at hard, unlimited at easy).
- **Skip:** Advances to the next word at no score, but breaks the streak.
- **Round timer:** Counts down from a per-difficulty starting value. Runs out → game over.
- **Bonus time:** Each correct answer adds bonus seconds to the timer (scaled by word length).

### Core Verbs
- **Click / tap** a letter tile → moves it to the next empty answer slot.
- **Click / tap** a placed letter → returns it to the tile pool.
- **Type** a letter key → places the matching unplaced tile.
- **Backspace** → removes the last placed tile.
- **Enter / Submit** → checks the answer.
- **Shuffle (Space)** → reshuffles remaining tiles.
- **Hint (H)** → reveals one letter.
- **Skip (Escape / S)** → skips current word.

### Game Modes
| Mode | Description |
|------|-------------|
| Classic Unscramble | Reconstruct one scrambled word per turn; timed round |
| Find-All Bonus | Find as many words as possible from a fixed letter set; separate score |
| Daily Challenge | One fixed word set shared globally per calendar date; no timer pressure, limited to 3 attempts |

### Input Scheme
- **Mouse:** Click letter tiles to place/remove; click Submit button.
- **Keyboard:** Letter keys place tiles; Backspace removes; Enter submits; Space reshuffles; H hints; S/Escape skips.
- **Touch:** Tap tiles on mobile (same as click). Large tap targets (min 48×48px).
- **Orientation:** Portrait-primary on mobile; landscape supported on desktop.

### Win / Lose / Fail Conditions
- **Correct answer:** Score awarded → brief celebration animation → next word auto-loads (0.8s delay).
- **Wrong answer:** Tiles shake, red flash, no score change, player can retry the same word.
- **Timer expires:** Game over; round summary shown.
- **Round complete (10 words):** Round summary + score + option to continue with new round.

### Difficulty Modes
| Difficulty | Word Length | Timer Start | Bonus Time/Word | Hint Limit |
|------------|------------|-------------|-----------------|-----------|
| Easy | 3–4 letters | 90s | +15s | Unlimited |
| Medium | 5–6 letters | 75s | +12s | 3 per round |
| Hard | 7–9 letters | 60s | +10s | 1 per round |
| Expert | 10–12 letters | 45s | +8s | 0 |

### Feedback Systems
- **Visual:** Tile "pop" animation on placement; green flash + confetti on correct; red shake on wrong.
- **Audio (WebAudio):** Soft click on tile place; success chime on correct; buzz on wrong; streak milestone fanfare at 5-streak.
- **Haptic:** Vibration pulse on correct (if browser supports `navigator.vibrate`).

---

## 4. Progression

### Session Progression
- Words cycle through the difficulty-appropriate word list in pseudorandom order (no repeats within a session).
- Timer countdown creates natural pacing pressure.
- Streak multiplier (×1 → ×2 → ×3) rewards consecutive correct answers without skips.

### Persistent Progression (localStorage)
| Metric | Persisted |
|--------|-----------|
| Best score per difficulty | Yes |
| Longest streak per difficulty | Yes |
| Total words solved lifetime | Yes |
| Daily challenge completion flag | Yes (date-keyed) |

### Unlock / Gating
- **Medium** unlocked from session start. [Estimated]
- **Hard** unlocks after scoring ≥ 500 on Medium. [Estimated]
- **Expert** unlocks after scoring ≥ 1 000 on Hard. [Estimated]
- Daily Challenge always available regardless of difficulty progress.

### No Prestige / Reset
- No prestige mechanic; players simply chase higher personal bests.

---

## 5. Economy & RNG (tables)

### Currencies
| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| Score Points | Session (non-persistent) | Correct answers, speed bonus, streak bonus | N/A (displayed only) |
| Hint Tokens | Session resource | Allocated per round by difficulty | Revealing a letter (-50 pts or from pool) |

### Score Formula [Estimated]
```
base_pts     = word_length × 10
speed_bonus  = floor(time_remaining_at_solve × 0.5)   // max 45pts on 90s timer
streak_mult  = min(3, 1 + floor(current_streak / 5))  // caps at ×3 after 10-streak
round_score  = (base_pts + speed_bonus) × streak_mult
hint_penalty = −50 pts per hint used (deducted from round total, min 0)
```

### Example Score Table [Estimated]
| Word Length | Base Pts | Speed Bonus (fast) | Speed Bonus (slow) | ×1 Total | ×3 Total |
|-------------|----------|-------------------|-------------------|----------|----------|
| 3 letters | 30 | 40 | 5 | 70 | 210 |
| 5 letters | 50 | 40 | 5 | 90 | 270 |
| 7 letters | 70 | 40 | 5 | 110 | 330 |
| 10 letters | 100 | 40 | 5 | 140 | 420 |

### RNG
- **Word selection:** Pseudorandom shuffle of the difficulty word list per session (Fisher-Yates). No duplicate within one session.
- **Tile shuffle:** Letters are randomly permuted at round start; the shuffle is guaranteed ≠ original word order (re-shuffle if identical).
- **No gacha / drop rates / loot boxes.** Pure skill-and-speed game.

---

## 6. Content Inventory

### Word Lists [Estimated — curated inline in the HTML]
| Difficulty | Word Length | Word Count | Example Words |
|------------|------------|------------|---------------|
| Easy | 3–4 | ~60 words | cat, dog, run, jump, fish, bird, hand, star, moon, lake |
| Medium | 5–6 | ~80 words | plant, storm, flame, crisp, bridge, frozen, gather, launch |
| Hard | 7–9 | ~80 words | blanket, program, crystal, splendid, mystery, absolute |
| Expert | 10–12 | ~40 words | strawberry, complicated, comfortable, disappear, environment |

### Hints
- Each word has one optional hint string embedded in the word list (e.g., "A pet that barks" for DOG).

### Find-All Bonus Pool [Estimated]
- ~15 curated 7-letter base sets, each guaranteed to contain ≥ 6 valid sub-words (3–7 letters).

### Daily Challenge
- One entry per calendar date, keyed as `YYYY-MM-DD` → index into a shuffled master list.

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract / typographic — floating letter tiles on a clean background; no explicit world or characters.
- **Premise:** No story. Pure puzzle challenge. The "narrative" is the player's personal journey from Easy to Expert.
- **Visual Theme:** Clean, modern word-puzzle aesthetic. Bright accent colors, white/light backgrounds, bold sans-serif fonts. Reminiscent of Wordle / NYT word games but with a warmer, more playful palette.
- **Tone:** Cheerful, encouraging, slightly competitive. Never punishing. Short success messages ("Nice!", "Blazing Fast!", "Streak Master!") maintain positive affect.
- **Writing Style:** Minimal copy; UI labels, short feedback strings, brief tooltip hints. No character dialogue.
- **IP:** Fully original; no licensed content.

---

## 8. Meta & Social Systems

### Daily Challenge [Estimated]
- One challenge set per calendar day, computed deterministically from the date.
- Shows "Today's Challenge" badge; completion state saved to localStorage.
- Shareable emoji-grid result string (similar to Wordle share) to clipboard.

### Achievements (local, no server)
| Achievement | Trigger |
|-------------|---------|
| First Word | Solve first word |
| Speed Demon | Solve with > 80% time remaining |
| On Fire | 5-word streak |
| Unstoppable | 10-word streak |
| Word Master | Solve 100 words lifetime |
| Expert Unlocked | Score 1 000 on Hard |

### Leaderboard
- Local high-score table per difficulty (top 5, name entry). No server leaderboard in MVP. [Estimated]

### Live-Ops
- Daily Challenge is the only live-ops requirement; it runs automatically from the embedded date-keyed word list (no server needed for the first year's supply of daily words).
- No seasonal events, no battle pass, no guild system in MVP.

### Social / Sharing
- "Copy Result" button generates a shareable text snippet with score + streak emoji grid.
- No in-game multiplayer; no friends list.

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| Splash / Title | App name, tagline, difficulty selector, Play + Daily Challenge buttons |
| How to Play | Modal overlay; 4-step illustrated guide, dismissable |
| Gameplay | Main puzzle screen (HUD + tiles + answer slots + controls) |
| Hint Pop-up | Inline notification showing what the hint revealed |
| Round Summary | Score, streak, time used, words solved, high-score badge, Play Again / Change Difficulty |
| Game Over (timer) | Time-up animation, final score, replay options |
| Find-All Bonus | Separate layout for multi-word mode; word bank, timer, found-words list |
| Settings Modal | Sound toggle, mute toggle, difficulty reset, clear saved data |
| Daily Challenge Result | Emoji share grid, score, streak |

### Settings / Options Menu
- Sound ON/OFF
- Mute toggle (silence all audio)
- Keyboard shortcuts reference
- Clear saved progress (with confirmation)
- About / Credits (one-liner)

### Gameplay HUD
```
[Difficulty badge]   [Score: 1250]   [Streak: 🔥7]   [Best: 2100]
[Timer bar ████████░░░░  42s]
              ← scrambled tiles →
              ← answer slots →
         [Shuffle]  [Hint (2)]  [Skip]
                  [Check / Submit]
```

### Navigation Flow
```
Splash → [Play] → Gameplay → [Correct × 10] → Round Summary → [Play Again] → Gameplay
                                             → [Change Difficulty] → Splash
       → [Daily] → Gameplay (Daily mode) → Daily Result → Share / Play Again
       → [?] → How to Play (modal, dismissable)
```

### Onboarding / Tutorial (first play)
1. Splash screen shown; "How to Play" modal auto-opens on first visit (localStorage flag).
2. Step 1: "You'll see a word — all scrambled up! Click the letters to spell it out."
3. Step 2: "Fill the answer slots in order. Tap a placed letter to remove it."
4. Step 3: "Hit Check (or Enter) when you're ready. Hints cost points — use sparingly!"
5. Step 4: "Go fast for a speed bonus. Keep a streak for a multiplier!"
6. Dismiss modal → first puzzle appears (Easy, 3-letter word).
7. First word always "CAT" (3 letters, obvious) to guarantee a fast first success. [Estimated]

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D flat** in a browser viewport; no camera concept. Single-screen layout.
- **Orientation:** Portrait-first on mobile; responsive layout adapts to landscape/desktop.
- **Aspect:** Fluid; uses CSS flexbox/grid; tested at 375px wide (iPhone SE) and 1440px wide.

### Art Style
- **Style:** Flat / material-inspired. Clean geometric shapes; rounded corners on tiles.
- **Palette:**
  - Background: #F8F7FF (soft off-white)
  - Tile (unplaced): #5C6BC0 (indigo) with white text
  - Tile (placed): #26C6DA (cyan) with white text
  - Correct: #66BB6A (green)
  - Wrong: #EF5350 (red)
  - Accent / streak: #FF7043 (orange-red)
  - Timer bar: gradient indigo → cyan
- **Typography:** "Segoe UI", system-ui, sans-serif; tiles use bold 1.4rem+; HUD 0.9rem.
- **Tiles:** 48–64px square on mobile, 56–72px on desktop; gap 6px; rounded 8px corners.

### Animation & VFX
- Tile placement: scale bounce (0.9 → 1.05 → 1.0, 150ms).
- Correct answer: green flash + CSS confetti burst (20 particles, 600ms).
- Wrong answer: red flash + horizontal shake (5px × 4 cycles, 300ms).
- Streak milestone: fire emoji pops at top with scale-in animation.
- Timer low (<10s): bar pulses red; background tints light pink.

### Audio (WebAudio API — no external files)
| Event | Sound Description |
|-------|------------------|
| Tile click | Soft "tick" (80Hz, 30ms) |
| Tile remove | Softer reverse tick |
| Correct word | Rising chime (C–E–G, 200ms each) |
| Wrong answer | Low buzz (200Hz, 150ms) |
| Hint used | Mid "bloop" (440Hz, 100ms) |
| Streak × 2 | Double chime |
| Streak × 3 | Triple fanfare |
| Game over | Descending tone |
| New best score | Ascending sparkle run |
- Mute toggle silences all WebAudio nodes instantly.

### Game Feel ("Juice")
- Every tile interaction has both a visual scale pop AND an audio tick.
- Correct answers never feel "flat" — always green + sound + brief particle spray.
- Timer urgency escalates via color shift and pulse; player always knows time state.

---

## 11. Monetization

### MVP (Open / Free)
- No ads, no IAP in the base open-source version.
- Entirely skill-based; nothing is paywalled.

### Optional Ad-Supported Layer [Estimated]
| Ad Type | Placement | Frequency |
|---------|-----------|-----------|
| Rewarded video | "Watch ad to get 3 hints" button on game-over screen | On demand |
| Interstitial | After every 3 rounds completed | ≤ 1 per 3 rounds |
| Banner | Below HUD during gameplay | Always-on (optional) |

### Optional IAP [Estimated]
| Product | Price |
|---------|-------|
| Remove Ads | $1.99 one-time |
| Dark Theme Pack | $0.99 |
| Extra Hint Pack (10) | $0.99 |

### Consent / Privacy
- GDPR/CCPA: If ads are added, a consent banner must appear before any tracking pixel fires.
- ATT prompt (iOS PWA): Required before IDFA access.
- Age gate: If targeting <13, no behavioral ads; no account creation.
- **MVP (no ads):** No consent screens required.

### Aggressiveness
- Minimal / non-existent in MVP. Purely skill-based free experience. [Confirmed by design intent]

---

## 12. Retention Hooks

### Daily Challenge
- One unique puzzle per calendar day; completion badge + shareable result.
- Returning daily is the primary retention driver.

### Streak System
- In-session combo multiplier (×1/×2/×3) rewards uninterrupted play.
- Lifetime "longest streak" saved to localStorage creates long-term goal.

### High Score Chase
- Per-difficulty best score displayed prominently; beating it triggers a "New Best!" animation.
- Local leaderboard (top 5 names + scores) stored in localStorage.

### Offline / Idle Earnings
- **None.** This is an active-play-only game; no offline accumulation mechanic.
- No energy/lives system; player can replay instantly.

### Push Notifications
- Not applicable for pure web (no service worker in MVP).
- PWA wrapper could add "Daily challenge is ready!" notification once/day. [Estimated]

### FOMO / Urgency
- Daily Challenge resets at midnight (date-keyed); missed days are gone.
- In-session timer creates real-time urgency.
- No artificial energy caps or hard paywalls.

---

## 13. Localization & Accessibility

### Language
- **English only in MVP.** [Estimated]
- Architecture supports i18n: all UI strings in a single `STRINGS` object for easy translation.
- No RTL support needed for English; add `dir="rtl"` and `lang=` attribute if Arabic/Hebrew added.

### Accessibility
- **Text scaling:** Em/rem units throughout; browser zoom supported to 200%.
- **Colorblind mode:** Not in MVP; planned: swap green/red for blue/orange (deuteranopia-safe) via settings toggle.
- **Keyboard-complete:** Full keyboard navigation; no mouse required.
- **ARIA labels:** Tiles labeled `aria-label="Letter A, unplaced"` etc. for screen-reader compatibility. [Estimated]
- **Focus indicators:** Visible focus ring on all interactive elements.
- **Tap targets:** Minimum 48×48px on mobile per WCAG 2.5.5.
- **Difficulty assist:** Easy mode acts as the built-in accessibility tier (longer timer, unlimited hints).

### Age / Content Rating
- **IARC 3+** — no violence, no mature language, no scary content. [Estimated]
- Suitable for COPPA / GDPR-K compliance (no personal data collection, no behavioral advertising in MVP).

### Regional Differences
- None in MVP. Word lists are British-English-friendly (no US-only slang in core lists).

---

## 14. Technical Structure

### Engine / Framework
- **Vanilla HTML5 + CSS3 + JavaScript (ES6+).** No framework, no build step.
- Single `.html` file; inline `<style>` and `<script>` tags.
- Runs by double-click; works fully offline.

### Platforms
- **Web (all modern browsers):** Chrome, Firefox, Safari, Edge.
- **Mobile:** Responsive CSS; tested at 375px (iPhone SE) and 390px (iPhone 14).
- **PWA:** Add `manifest.json` + service worker for installability (Phase 2).

### Save System
- **localStorage** (client-side only): best scores, longest streaks, lifetime word count, daily-challenge completion flags, settings (mute state, difficulty unlock flags).
- No server, no accounts, no cloud sync in MVP.

### Multiplayer / Netcode
- None. Single-player only. Anti-cheat not applicable.

### Backend / Analytics
- None in MVP. Add Plausible or simple ping analytics in Phase 2 if desired.
- Remote config: not needed; word lists and difficulty params are inline.

### Word Validation (Find-All Mode)
- Inline dictionary: a compact trie or sorted array of ~10 000 common English words (3–8 letters), embedded as a JS constant. No network lookup.

### Performance
- Target: < 200KB total page size (uncompressed).
- No images (pure CSS tiles); WebAudio generates SFX procedurally.
- First paint < 500ms on 3G.

### Third-Party SDKs
- None in MVP. No ad SDK, no analytics SDK, no auth SDK.

---

## 15. Pacing & Difficulty Curve

### Early Game (Easy, sessions 1–5)
- 3–4 letter words; generous 90s timer; unlimited hints.
- Players learn tap-to-place mechanics organically.
- First-time tutorial modal ensures zero-confusion onboarding.
- High success rate (≥ 80% expected solve rate) builds confidence.

### Mid Game (Medium, sessions 6–20)
- 5–6 letter words; 75s timer; 3 hints per round.
- Speed bonus becomes meaningful; streak multiplier starts mattering.
- Players begin developing word-recognition pattern skills.
- Churn risk here: if words are too obscure, players drop off. Word list must stay to common vocabulary. [Estimated]

### Late Game (Hard / Expert, sessions 21+)
- 7–12 letter words; short timers; few/no hints.
- Players who reach Expert are word-game enthusiasts; high intrinsic motivation.
- Daily Challenge provides a daily destination that bridges all difficulty levels.

### Milestone "Aha" Moments
1. First ×2 streak multiplier activation (~word 5).
2. First "New Best!" score celebration.
3. Hard difficulty unlock.
4. First 10-word streak ("Unstoppable!" achievement).
5. Daily Challenge completion and share.

### Churn Points
- Easy → Medium transition: some players plateau here. Mitigated by difficulty-unlock reward framing.
- Timer pressure on Expert: if the first Expert word takes >45s, instant game-over is frustrating. Mitigate by giving 1 bonus second per letter of the first Expert word. [Estimated]
- Find-All mode: complexity spike vs Classic; must be clearly framed as optional bonus. [Estimated]

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist
| Feature | MVP | Full |
|---------|-----|------|
| Classic Unscramble mode | ✓ | ✓ |
| 4 difficulty levels | ✓ | ✓ |
| Tap + keyboard input | ✓ | ✓ |
| Timer + score + streak | ✓ | ✓ |
| Shuffle / Hint / Skip | ✓ | ✓ |
| WebAudio SFX + mute | ✓ | ✓ |
| localStorage persistence | ✓ | ✓ |
| Responsive mobile layout | ✓ | ✓ |
| How-to-play tutorial | ✓ | ✓ |
| Round summary + replay | ✓ | ✓ |
| Find-All bonus mode | ✓ | ✓ |
| Daily Challenge | ✓ | ✓ |
| Emoji share result | ✓ | ✓ |
| Local leaderboard (top 5) | ✓ | ✓ |
| Achievements (local) | – | ✓ |
| PWA / installable | – | ✓ |
| Colorblind mode | – | ✓ |
| Server leaderboard | – | ✓ |
| Multiplayer (async) | – | ✓ |
| i18n (multi-language) | – | ✓ |
| Ad integration | – | Optional |

### Phased Roadmap
**Phase 1 — Core (Week 1)**
- HTML shell + CSS tile grid layout.
- Word list embedding (Easy + Medium).
- Tile click-to-place / click-to-remove logic.
- Keyboard input (letter, Backspace, Enter).
- Answer validation (exact match).
- Score formula + display.
- Timer countdown.
- Shuffle + guaranteed-different logic.

**Phase 2 — Game Feel (Week 1–2)**
- Animations: bounce, shake, flash.
- WebAudio SFX: click, correct, wrong, streak.
- Streak multiplier + visual indicator.
- Speed bonus calculation.
- Hint system (reveal next correct letter).
- Skip logic (streak break).
- Hard + Expert word lists.

**Phase 3 — Meta & Polish (Week 2)**
- Round summary screen.
- localStorage best-score + streak persistence.
- Difficulty selector + unlock gating.
- How-to-play modal.
- Game-over screen.
- Responsive CSS polish (mobile + desktop).
- Mute toggle.

**Phase 4 — Bonus & Daily (Week 3)**
- Find-All bonus mode (multi-word from one letter set).
- Inline mini-dictionary for Find-All validation.
- Daily Challenge (date-keyed word selection, completion flag).
- Emoji share string generator.
- Local leaderboard (top 5 name entry).
- Achievement system.

**Phase 5 — Extended (Week 4+)**
- PWA manifest + service worker.
- Colorblind mode.
- ARIA accessibility pass.
- Analytics (privacy-preserving).
- Optional ad integration.

### Recommended Tech Stack (Clone)
- **Language:** Vanilla JavaScript ES6+ (no framework needed)
- **Styling:** CSS3 with custom properties (variables for theming)
- **Audio:** Web Audio API (procedural SFX, zero external files)
- **Storage:** localStorage
- **Build:** None — single `.html` file
- **Hosting:** GitHub Pages, Netlify, Vercel (all free)

### Required Asset List
| Asset | Type | Source |
|-------|------|--------|
| Word lists (Easy/Medium/Hard/Expert) | Inline JS arrays | Curated manually / public domain lists |
| Hint strings | Inline JS strings (one per word) | Written manually |
| Find-All letter sets | Inline JS arrays | Curated manually |
| Mini-dictionary (Find-All) | Inline JS Set/array ~10K words | Public domain (SCOWL / enable1) |
| Daily word schedule | Inline JS array (365 entries) | Generated from word lists |
| SFX | Procedural WebAudio | No files needed |
| Icons | CSS / Unicode emoji | No files needed |
| Fonts | System fonts | No files needed |

### Hardest Parts / Risks
1. **Shuffle guarantee:** Must ensure the shuffled order ≠ the original word every time. Simple: loop Fisher-Yates until result ≠ original. For short words (3 letters), may need multiple attempts.
2. **Find-All dictionary:** Embedding a 10K-word dictionary inline adds ~50KB. Must balance coverage vs file size. Consider using a frequency-ranked top-5K list.
3. **Tile UX on mobile:** Small screens with 10–12 letter tiles need dynamic font scaling. CSS `clamp()` + flexbox wrap handles this but needs careful testing.
4. **Answer validation edge cases:** Accented characters (e.g., "naïve") should be normalized. MVP uses ASCII-only words to avoid this.
5. **Daily Challenge sync:** Without a server, the date-keyed index approach works but "spoofing" is trivially possible. Acceptable for a non-competitive implementation.

---

## 17. Open Questions

1. **Find-All dictionary completeness:** The inline mini-dictionary will inevitably miss some valid words (e.g., archaic or regional words). Players may feel cheated when valid words are rejected. Mitigation: use a well-known public list (SCOWL "medium" frequency) and note in the UI that the game uses a "common words" dictionary.
2. **Expert difficulty churn rate:** The 45-second timer for 10–12 letter words may be too punishing for casual players. Needs playtesting to calibrate — the bonus-time-per-letter mitigation is speculative.
3. **Colorblind accessibility:** The current red/green correct/wrong palette is not colorblind-safe. A settings toggle for alternative colors is flagged as Phase 5 but should be validated with real users.
4. **Daily Challenge content:** 365 entries covers year 1; beyond that, recycling or adding server-driven content is needed. [Estimated: acceptable for MVP]
5. **Keyboard layout for Find-All mode:** The multi-word input UX (clearing the answer area after each submission, showing found words) needs UX testing to confirm it doesn't feel clunky on mobile.
