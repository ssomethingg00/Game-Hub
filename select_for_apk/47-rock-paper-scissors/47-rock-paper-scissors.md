# Rock Paper Scissors — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Rock Paper Scissors (RPS) is one of the oldest simultaneous-decision zero-sum games in the world, played between two parties who simultaneously form one of three shapes. This web game clone delivers the classic RPS experience plus the extended "Rock Paper Scissors Lizard Spock" (RPSLS) variant popularized by The Big Bang Theory. Players tap/click a choice, watch an animated reveal countdown ("Rock… Paper… Scissors… SHOOT!"), then see both hands and the outcome (Win/Lose/Draw). Scores track across rounds; a "First to N" match mode adds stakes. Light AI pattern-detection on Hard difficulty gives the game longevity beyond pure luck.

**Quick facts:**
- Developer: Original clone (public-domain rules)
- Platform: Web (HTML5, single file)
- Release: 2026
- Age Rating: ESRB E (Everyone) [Confirmed — no violence, suitable all ages]
- Monetization: None (free, no ads, no IAP)
- Session length: 2–10 minutes [Estimated]
- Play style: Active, single-player vs CPU

---

## 2. Core Loops

- **30-second loop:** Player taps one of 3 (or 5 in RPSLS) choice buttons → countdown animation plays → both choices revealed simultaneously with emoji hands → outcome banner flashes Win/Lose/Draw → scores update → next round begins immediately.
- **Session loop:** Play rounds until one side reaches the "First to N" win threshold → match-over screen shows final score and winner → player resets for another match, or switches mode (RPSLS toggle, difficulty, match length).
- **Meta loop:** Persistent win/loss/draw record stored in localStorage motivates beating your all-time win rate; Hard mode AI pattern detection provides a skill challenge that casual mode lacks; streak counter encourages long unbroken win runs.

---

## 3. Mechanics, Controls & Game States

### Core Rules

**Standard RPS — outcome table [Confirmed]:**

| Player \ CPU | Rock | Paper | Scissors |
|---|---|---|---|
| Rock | Draw | Lose | Win |
| Paper | Win | Draw | Lose |
| Scissors | Lose | Win | Draw |

**RPSLS — outcome table [Confirmed — from original Sheldon Cooper rules]:**

| Wins against | Rock | Paper | Scissors | Lizard | Spock |
|---|---|---|---|---|---|
| Rock | Draw | Lose | Win | Win | Lose |
| Paper | Win | Draw | Lose | Lose | Win |
| Scissors | Lose | Win | Draw | Win | Lose |
| Lizard | Lose | Win | Lose | Draw | Win |
| Spock | Win | Lose | Win | Lose | Draw |

Extended rules verbal: Scissors cuts Paper, Paper covers Rock, Rock crushes Lizard, Lizard poisons Spock, Spock smashes Scissors, Scissors decapitates Lizard, Lizard eats Paper, Paper disproves Spock, Spock vaporizes Rock, Rock crushes Scissors.

### Core Verbs
- **Choose** — tap/click a weapon button
- **Watch** — observe the countdown reveal animation
- **Read** — interpret the result banner and score update
- **Reset** — start a new match or round

### Game Modes
1. **Endless/Arcade** — no match limit; just play rounds and track running score (You vs CPU vs Draws).
2. **First to N** — configurable N (3, 5, 7, 10); whoever reaches N wins first ends the match. Match-win screen shown.
3. **Easy AI** — CPU picks truly randomly (uniform distribution, 33.3% each choice).
4. **Hard AI** — CPU uses a simple Markov/frequency tracker: records last 5–10 player moves, identifies the most-played choice, and counters it. Has ~60% win rate vs pattern-y humans. [Estimated — based on standard RPS AI research]

### Input Scheme
- Mouse click (desktop) and touch tap (mobile) on choice buttons.
- Portrait orientation preferred; landscape also supported.
- Buttons lock (disabled) during countdown animation to prevent multi-input.

### Win / Lose / Fail Conditions
- **Round Win:** Player's choice beats CPU's choice per the outcome table.
- **Round Loss:** CPU's choice beats player's choice.
- **Round Draw:** Both choices identical.
- **Match Win (First to N mode):** First player to reach N round wins.
- **Match Loss:** CPU reaches N round wins first.
- **Failure handling:** No penalty beyond score increment. Instant retry. No lives/energy.

### Feedback Systems
- Visual: result banner color (green Win, red Lose, yellow Draw); hand emoji scale bounce on reveal; shake animation on loss.
- Audio: synthesized WebAudio SFX — countdown tick, reveal "shoot" sound, win fanfare, lose thud, draw neutral ping.
- Haptic: not implemented (web limitation).

---

## 4. Progression

**No traditional progression system** [Confirmed — RPS is a stateless skill game].

Pseudo-progression elements:
- **Running score:** Cumulative You/CPU/Draw counts for the session.
- **All-time record:** localStorage-persisted total wins/losses/draws across all sessions.
- **Current streak:** Consecutive wins in current session.
- **Best streak:** All-time best win streak (localStorage).
- **Match mode goal:** Reaching First-to-N threshold creates micro-progression within a match.
- **RPSLS unlock:** Available as a toggle (no unlock gate — player choice) [Estimated — adds variety without gatekeeping].

No XP, no levels, no upgrade trees, no prestige. This is intentional for a pure skill/luck game. [Confirmed by design]

---

## 5. Economy & RNG *(tables)*

### Currencies

| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| None | — | — | — |

No economy. No currency. No monetization. [Confirmed by design]

### RNG

| Mode | CPU Choice Distribution |
|---|---|
| Easy (Random) | Uniform: Rock 33.3%, Paper 33.3%, Scissors 33.3% (or each of 5 in RPSLS: 20% each) |
| Hard (Adaptive) | Counters player's most-frequent choice; uniform if no clear pattern (< 3 rounds played) |

**Hard mode algorithm [Estimated]:**
- Track frequency of player's last N choices (N = min(roundsPlayed, 10)).
- Find player's most common choice (mode of distribution).
- CPU plays the choice that beats the player's most common choice with probability 0.65; random otherwise (probability 0.35) — adds noise so player can exploit by switching.
- Ties in frequency → uniform random among tied choices.

**No gacha, no loot tables, no drop rates.** RNG limited to CPU choice selection only.

---

## 6. Content Inventory *(counts + lists)*

### Weapons

**Standard RPS (3 weapons):**
- Rock — emoji: ✊ / 🪨
- Paper — emoji: ✋ / 📄
- Scissors — emoji: ✌️ / ✂️

**RPSLS additions (2 more weapons):**
- Lizard — emoji: 🦎 / 🤏
- Spock — emoji: 🖖

### Result Flavour Text [Estimated — 2–3 per outcome for variety]

| Outcome | Phrases |
|---|---|
| Win | "You win!", "Nice move!", "You crushed it!" |
| Lose | "CPU wins!", "Better luck next time!", "Ouch!" |
| Draw | "It's a draw!", "Great minds think alike!", "Same wavelength!" |

### Match Length Options [Estimated]
- Best of 1 (Arcade/Endless)
- First to 3
- First to 5
- First to 7
- First to 10

### Difficulty Options
- Easy (random CPU)
- Hard (adaptive CPU)

**Total screens:** 1 (single-page app) [Confirmed by design]
**Total sound effects:** 6 (tick × 3, shoot, win, lose, draw) [Estimated]

---

## 7. Theme, Narrative & Tone

**Setting:** Abstract — colorful flat UI, no physical world setting.
**Premise:** No story. Pure game. [Confirmed — RPS is a ruleset, not a narrative]
**Tone:** Playful, fast, slightly cheeky. Result banners have light personality ("You crushed it!" / "Ouch!"). Not competitive-serious; casual fun.
**Visual theme:** Bold emoji hands on colored cards; clean sans-serif typography; vibrant but not garish palette (deep purple background, bright accent cards for each weapon).
**Character personalities:** None — player is "You", opponent is "CPU". [Confirmed]
**Dialogue/writing style:** Ultra-short, punchy result lines. How-to text is concise bullet points.
**Licensed IP:** None. RPSLS rules are public knowledge; "Lizard Spock" name is not used to avoid CBS/Warner trademark concerns — clone refers to the variant as "RPSLS" or "Big Five" mode. [Estimated caution]

---

## 8. Meta & Social Systems

**Minimal meta, by design:**
- **All-time record** (localStorage): total wins/losses/draws survive browser close.
- **Best streak** (localStorage): motivates replaying.
- **No daily missions, no battle pass, no events, no seasons.** [Confirmed — not a live-service game]
- **No multiplayer, no leaderboards, no guilds.** [Confirmed — single-player only]
- **No social sharing** beyond player copying their score manually.
- **Live-ops cadence:** None. Static game with no ongoing content updates required. [Confirmed]
- **Reset all stats** option in settings for clean slate.

---

## 9. UI/UX & Screen Map

### Screens (all in one HTML page, sections show/hide)

| Screen | Purpose |
|---|---|
| **Main / Game** | Core gameplay: choice buttons, reveal area, score HUD, result banner |
| **Match-Win Modal** | Shows who won the match, final score, Play Again / New Match buttons |
| **How-To Modal** | Rules explanation, outcome table, RPSLS rules if toggled on |
| **Settings Panel** | Mode toggle (RPS/RPSLS), difficulty (Easy/Hard), match length, mute, reset stats |

### Settings/Options Menu Contents
- Game Mode: RPS (3-choice) / RPSLS (5-choice)
- Difficulty: Easy / Hard
- Match Length: Endless / First to 3 / 5 / 7 / 10
- Sound: Mute toggle
- Reset All Stats (clears localStorage record)
- Credits / Version

### Gameplay HUD Elements
- Score display: YOU [N] — CPU [N] — DRAW [N]
- Current match progress (e.g. "Round 4 | You: 2 — CPU: 1 | First to 5")
- Current streak indicator ("Win streak: 3 🔥")
- Best streak ("Best: 7")
- All-time record ("All-time: 42W / 38L / 15D")
- Result banner (large, centered, color-coded)
- Both hands reveal area (player left, CPU right, vs divider center)
- 3 or 5 choice buttons (bottom/center)
- Settings gear icon, How-To "?" icon, Mute icon (top bar)

### Navigation Flow
```
[Game Main]
  ├── [Settings Panel] (gear icon toggle)
  ├── [How-To Modal] (? icon)
  └── [Match-Win Modal] (auto on match end)
        ├── [Play Again] → reset match, stay in Game Main
        └── [Change Settings] → open Settings Panel
```

### Onboarding / Tutorial (first-time-user flow)
1. On first load (no localStorage key), show brief tooltip overlay: "Tap Rock, Paper, or Scissors to play!"
2. After first pick: tooltip fades, game proceeds normally.
3. How-To button always accessible for rule reference.
4. RPSLS mode shows extended outcome table when How-To is opened.
5. No forced tutorial — player can dismiss and explore freely.
6. First match-win triggers brief celebration (confetti burst) to reinforce the loop.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- 2D, no camera — static layout. [Confirmed]
- Portrait-first single-page UI; desktop centers content with max-width ~500px.

### Art Style
- Flat design, minimal shadows, rounded corners.
- Large emoji for weapon representations (cross-browser emoji rendering).
- Color palette:
  - Background: deep indigo (#1a1a2e) or dark purple
  - Card/button base: semi-transparent white overlay
  - Rock accent: warm orange (#ff6b35)
  - Paper accent: sky blue (#4ecdc4)
  - Scissors accent: rose pink (#ff6b9d)
  - Lizard accent: lime green (#a8ff3e) [RPSLS]
  - Spock accent: electric blue (#00b4ff) [RPSLS]
  - Win: #2ecc71 (green)
  - Lose: #e74c3c (red)
  - Draw: #f39c12 (amber)

### Animations
- **Countdown:** "Rock… Paper… Scissors… SHOOT!" text pulses in 1-second intervals (3 beats + reveal). Weapon buttons show subtle bounce synced to each beat.
- **Reveal:** Both chosen emoji scale up (0→1.2→1.0) with a brief elastic spring effect.
- **Win:** Player hand pulses, confetti particle burst (pure CSS/JS, no canvas required).
- **Lose:** Screen gentle shake (CSS keyframe translateX).
- **Draw:** Both hands bounce once simultaneously.
- **Button hover:** Scale 1.05, brightness +10%.
- **Button active/selected:** Scale 0.95 depression effect.

### VFX
- CSS confetti on match win (colored divs with random rotation/fall animation).
- Result banner slides in from top (translateY transition).
- Score counters animate count-up on change.

### Audio (WebAudio API — no external files)
- **Tick (×3):** Short sine wave blip, descending pitch (440Hz, 330Hz, 220Hz), 0.1s.
- **SHOOT:** Percussive noise burst, 0.15s.
- **Win:** Ascending 3-note chime (C-E-G), 0.5s.
- **Lose:** Descending 2-note thud (C-A♭), 0.3s.
- **Draw:** Neutral single tone (D4), 0.2s.
- **Match Win:** Ascending 5-note fanfare, 0.8s.
- Mute toggle (localStorage persisted).

---

## 11. Monetization

**None.** [Confirmed — free, standalone web game with no ads or IAP by design]

- No banners, interstitials, rewarded ads, or offerwall.
- No IAP products.
- No subscriptions or battle pass.
- No ATT prompt, no GDPR CMP, no age gate required (no data collection, no tracking, no advertising).
- **Privacy:** Zero user data leaves the device. localStorage only, no network calls. COPPA/GDPR-K not applicable (no data collected). [Confirmed]

---

## 12. Retention Hooks

| Hook | Implementation | Notes |
|---|---|---|
| All-time record | localStorage persisted W/L/D | Motivates beating personal record |
| Best win streak | localStorage persisted | Competitive goal vs self |
| Match mode | First-to-N creates match arcs | Session structure keeps players in |
| Hard mode AI | Skill challenge beyond luck | Replayability for engaged players |
| RPSLS toggle | Variety/novelty | Extends replay appeal |
| Session streak display | Real-time "Win streak: N 🔥" | In-the-moment excitement |

**No idle/offline earnings** — game is session-only, no background progression. [Confirmed by design]
**No push notifications** — web game, no notification API used. [Confirmed]
**No energy/lives/FOMO mechanics.** [Confirmed — unlimited play]

---

## 13. Localization & Accessibility

### Languages
- English only (v1). [Estimated — clone adds more if needed]
- No RTL support required for English.

### Accessibility [Estimated — best practices applied]
- All choice buttons keyboard-accessible (Tab focus, Enter/Space to activate).
- Focus ring visible on keyboard navigation.
- ARIA labels on all buttons ("Choose Rock", "Choose Paper", etc.).
- Result banner announced via ARIA live region (aria-live="assertive") for screen readers.
- Color-coded results also show text labels (not color-only).
- Emoji fallback: if emoji don't render, text label shown below.
- No flashing >3Hz; animations respect `prefers-reduced-motion` (skip animations if set).
- Touch targets minimum 44×44px per WCAG 2.5.5.
- Font size minimum 16px body text.

### Age/Content Rating
- ESRB: E (Everyone). [Confirmed — no violence, language, or adult content]
- COPPA compliance: not required (no data collection). [Confirmed]
- GDPR-K: not applicable. [Confirmed]

### Regional Differences
- None — RPS is universal. RPSLS pop-culture reference is English-language, but rules display in outcome table regardless.

---

## 14. Technical Structure

### Engine / Framework
- Vanilla HTML5 + CSS3 + JavaScript (ES6+). No framework, no build step. [Confirmed by design]
- Single .html file, fully self-contained. Runs by double-click, offline. [Confirmed]

### Platforms
- Any modern browser (Chrome 90+, Firefox 88+, Safari 14+, Edge 90+). [Estimated]
- Mobile browsers: iOS Safari, Android Chrome. [Confirmed by responsive design]
- Offline capable — no network requests at runtime.

### Save System
- localStorage only (client-side, no server).
- Keys: `rps_alltime_wins`, `rps_alltime_losses`, `rps_alltime_draws`, `rps_best_streak`, `rps_settings_mode`, `rps_settings_difficulty`, `rps_settings_match_length`, `rps_settings_muted`.
- No accounts, no auth, no cloud sync.

### Multiplayer / Netcode
- None — single-player vs CPU. [Confirmed]

### Anti-Cheat / Server Authority
- N/A — single-player, client-side only. AI logic is client-side; no competitive integrity concern. [Confirmed]

### Backend Services
- None. Zero backend. [Confirmed]

### Analytics
- None. [Confirmed — no tracking]

### Third-Party SDKs
- None. [Confirmed]

### App Size
- Estimated ~20–50 KB (single HTML file, all inline). [Estimated]

### Performance Notes
- No canvas, no WebGL. Pure DOM + CSS animations. 60fps on any device made after 2015. [Estimated]
- WebAudio API for sounds — programmatically generated, no audio files loaded.

---

## 15. Pacing & Difficulty

### Pacing

**Early game (rounds 1–5):**
- Player learns the loop: click → countdown → reveal → result.
- Tutorial tooltip on first play.
- Easy mode: purely luck, 33% win rate.
- Rounds take ~4 seconds each (3-beat countdown + reveal + 1s result display).

**Mid game (rounds 5–15, or first match arc):**
- Pattern recognition sets in. Player may notice they over-pick one weapon.
- Hard mode AI starts adapting after ~3 rounds, win rate shifts toward CPU.
- First match completion (First to 5) provides a natural break point.

**Late game / ongoing:**
- Hard mode creates genuine challenge: pattern-breaking becomes the skill.
- Best streak and all-time record become long-term goals.
- RPSLS mode resets early-game discovery feeling.

### Difficulty Curve
- Easy: Flat. ~50% expected win rate (tied with draws removed). [Confirmed — law of large numbers]
- Hard: Progressive. First 3 rounds random, then adaptive. Player who varies picks stays competitive; repetitive player loses. [Estimated]

### Key Milestone Beats
1. First win → result banner, score updates, "plays" feeling reinforced.
2. First match win (First to N) → confetti, match-win modal.
3. Win streak of 3+ → streak counter lights up with flame emoji.
4. First Hard mode loss streak → player realizes AI is adapting.
5. First RPSLS game → novelty, new choices to explore.

### Churn Points
- If player is on a long lose streak vs Hard mode, may feel AI is "cheating" — addressed with transparent Hard mode description in How-To. [Estimated]
- Endless mode without match structure can feel aimless — mitigated by offering First-to-N as default. [Estimated]
- No paywalls, no energy walls, no churn from monetization. [Confirmed]

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---|---|---|
| 3-choice RPS gameplay | ✅ | ✅ |
| Countdown reveal animation | ✅ | ✅ |
| Win/Lose/Draw determination | ✅ | ✅ |
| Running score (You/CPU/Draw) | ✅ | ✅ |
| Easy (random) CPU | ✅ | ✅ |
| First-to-N match mode | ✅ | ✅ |
| Match-win screen | ✅ | ✅ |
| Responsive/mobile layout | ✅ | ✅ |
| localStorage persistence | ✅ | ✅ |
| RPSLS 5-choice mode | — | ✅ |
| Hard (adaptive) AI | — | ✅ |
| WebAudio SFX | — | ✅ |
| Mute toggle | — | ✅ |
| Streak counter | — | ✅ |
| All-time record | — | ✅ |
| How-To modal | — | ✅ |
| Settings panel | — | ✅ |
| Confetti match-win VFX | — | ✅ |
| Keyboard accessibility | — | ✅ |
| prefers-reduced-motion | — | ✅ |

### Phased Roadmap

**Phase 1 — Core (Day 1):**
- HTML structure: HUD, choice buttons, reveal area, result banner.
- RPS outcome logic (3×3 table).
- Countdown animation (3 beats + reveal).
- Button lock during animation.
- Score tracking (session).
- Win/Lose/Draw display.

**Phase 2 — Match + Persistence (Day 1–2):**
- First-to-N match mode with configurable N.
- Match-win modal.
- localStorage: all-time record + settings.
- Reset stats option.

**Phase 3 — Polish + AI (Day 2):**
- Hard mode adaptive AI (frequency tracker).
- Streak counter (current + best).
- WebAudio SFX (6 sounds, programmatic synthesis).
- Mute toggle.

**Phase 4 — RPSLS + UX (Day 2–3):**
- 5-choice RPSLS mode with correct 5×5 outcome table.
- Mode toggle (RPS ↔ RPSLS).
- Settings panel UI.
- How-To modal with outcome tables.
- Responsive CSS polish.
- Accessibility (ARIA, keyboard nav, reduced-motion).

**Phase 5 — QA (Day 3):**
- Test all outcome combinations for both modes.
- Test match mode edge cases (tie-break on last round).
- Test localStorage persistence across reloads.
- Test on mobile (iOS Safari, Android Chrome).
- Verify no console errors.

### Recommended Tech Stack
- HTML5 (semantic elements, no framework).
- CSS3 (custom properties, flexbox, keyframe animations, media queries).
- Vanilla JavaScript ES6+ (classes optional, modules not needed for single file).
- Web Audio API for SFX.
- localStorage API for persistence.
- No npm, no bundler, no dependencies.

### Required Asset List
- All assets generated in-code [Confirmed]:
  - Weapon icons: Unicode emoji (✊✋✌️🦎🖖).
  - SFX: WebAudio synthesized.
  - Fonts: system-ui / sans-serif stack (no web font fetch).
  - Colors: CSS custom properties.
  - Animations: CSS keyframes + JS class toggles.

### Hardest Parts / Risks
1. **Countdown timing sync:** Locking buttons, running animation beats, and unlocking at exactly the right moment requires careful setTimeout/Promise chaining. Race condition if player taps twice quickly.
2. **RPSLS outcome table correctness:** 5×5 = 25 outcomes, 10 are ties, 15 need directional correct assignment. Easy to introduce a bug. Verify with unit tests or console assertions.
3. **Hard AI balance:** Too strong → frustrating; too weak → pointless. The 65%/35% noise factor is the key tuning lever. Should feel beatable with deliberate strategy.
4. **Cross-browser emoji rendering:** Emoji appearance varies significantly between Windows, macOS, iOS, Android. Consistent sizing via `font-size` and `line-height` normalization required.
5. **WebAudio on iOS:** iOS Safari requires user gesture to unlock AudioContext. Must create AudioContext on first button tap, not on page load.

---

## 17. Open Questions

1. **Hard mode pattern window size:** Is 10 the right lookback? Smaller window (5) reacts faster to strategy changes; larger window (15) is harder to trick. Needs playtesting to tune. [Estimated: 8 rounds as starting point]
2. **Optimal countdown speed:** 3-beat countdown at 1s/beat = 3s feels satisfying but may be slow for repeat players. Consider adding a "Fast" mode option (0.5s beats). [Estimated]
3. **RPSLS variant naming:** "Lizard Spock" is associated with The Big Bang Theory (CBS/WB). Using the descriptor "5-Weapon Mode" or "Extended Mode" in marketing copy avoids any trademark concern, though the rules themselves are public domain. Legal review recommended before commercial distribution.
4. **Sound design polish:** The programmatic WebAudio sounds are functional but basic. A sound designer could replace them with recorded SFX for a significantly more polished feel without changing any game logic.

---

*Blueprint version 1.0 — Built for a browser-native single-file RPS clone. All mechanics confirmed from public-domain RPS rules; RPSLS rules confirmed from published Sheldon Cooper variant. All estimates are build-ready starting points for tuning.*
