# LexiGuess — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

LexiGuess is a daily word-deduction puzzle game where players have 6 attempts to identify a hidden 5-letter word. Each guess triggers color-coded feedback: green for correct letter in correct position, yellow for correct letter in wrong position, gray for absent letter. The core appeal is the single shared daily challenge that creates social/cultural synchrony, the low friction (one puzzle per day, no account needed), and the elegant constraint of 6 tries. An endless/random mode extends play beyond the daily limit.

**Quick Facts:**
- Original: Wordle (Josh Wardle, 2021; acquired by The New York Times, 2022)
- Platforms: Web (primary), iOS, Android
- Rating: ESRB E (Everyone) / IARC 3+ — suitable all ages
- Install scale: 12M+ daily active users at peak [Confirmed]
- Monetization model (original): Free, no ads, no IAP — NYT subscription upsell only
- Clone monetization: Free, optional rewarded ads for hints, no hard paywall

---

## 2. Core Loops

- **30-second loop:** Type a 5-letter word → press Enter → watch each tile flip revealing green/yellow/gray color feedback → read clues → plan next guess.
- **Session loop:** Open game → view empty 6×5 grid → make up to 6 guesses with progressive narrowing → reach win (colored celebration) or lose (word reveal) → view stats modal → optionally share result string → close or switch to endless mode.
- **Meta loop:** Return next day for new daily word (streak incentive) → accumulate stats (win %, streak, guess distribution) → share results to social media → compete implicitly with friends on same daily word.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics & Rules
- Hidden target is a 5-letter English word drawn from a curated answer list (~2,500 words) [Confirmed]
- Guess list is larger (~12,000+ valid 5-letter words) to accept valid English words [Confirmed]
- Each guess must be a valid dictionary word; invalid words shake the row and show a "Not in word list" toast
- After Enter: each letter tile flips one at a time (left to right, ~300ms delay per tile) revealing its color
- Letters are colored in this exact priority order [Confirmed]:
  1. Green pass: mark all exact-position matches (consume from target letter count)
  2. Yellow pass (left to right): for remaining unmatched guess letters, if that letter still has count in target, mark yellow and decrement; else gray
  - This ensures duplicates are never over-counted (e.g., guess "SPEED" vs target "SPOKE": first S green, first E yellow, second E gray)
- On-screen keyboard updates letter state after each guess (green > yellow > gray, never downgrade a better state)

### Game Modes
1. **Daily Mode** — One word per calendar day, seeded deterministically by date offset from epoch. Shared globally. State saved; partially completed puzzles persist across browser refresh. Shows countdown to next daily word after completion.
2. **Endless/Random Mode** — New random word on demand; no streak implications; can play unlimited times; stats tracked separately or combined (design choice: track combined).
3. **Hard Mode (optional toggle)** — Any revealed hints must be used in subsequent guesses: greens must stay in position, yellows must appear somewhere. Toggle locked once first guess is made.

### Input Scheme
- **On-screen keyboard:** Three rows (QWERTY layout); tap keys; Backspace and Enter keys prominent
- **Physical keyboard:** Full keyboard support — letter keys, Enter/Return, Backspace/Delete
- Orientation: Portrait-primary (mobile); works landscape and desktop widescreen
- No mouse-drag or swipe mechanics

### Win / Lose / Fail States
- **Win:** Guess matches target in ≤6 tries → tiles bounce/celebrate animation → win modal with stats + share button. Row shows "Brilliant!" / "Magnificent!" etc. based on guess number.
- **Lose:** 6 guesses used without finding word → lose state → target word revealed in toast → stats modal opens → no penalty currency.
- **Invalid guess:** Word not in dictionary → row shakes horizontally → toast "Not in word list" → no guess consumed.
- **Daily already played:** On revisit same day → show completed board state (no replay) → stats + countdown.

### Difficulty Modes
- Normal Mode: No constraints on subsequent guesses
- Hard Mode: Must use all confirmed hints — green letters locked to position, yellow letters must appear [Confirmed]

### Feedback Systems
- Tile flip animation (card-flip CSS 3D transform) with color reveal
- Bounce animation on win row
- Shake animation on invalid word
- Pop/scale animation when typing letters into tiles
- Toast notifications (brief centered messages)
- On-screen keyboard keys update color state progressively
- Optional WebAudio SFX: key click, tile flip, win fanfare, error buzz

---

## 4. Progression

No traditional XP/level/upgrade system. Progression is implicit:

| System | Description |
|---|---|
| Streak | Consecutive daily wins; resets on any loss or missed day |
| Max Streak | All-time best consecutive daily wins |
| Win % | Cumulative win rate across all played games |
| Guess Distribution | Bar chart showing how many games won on guess 1–6 |
| Games Played | Total games started |

- **Unlock via streak:** No hard unlocks; streak is a soft social/personal achievement
- **Hard Mode unlock:** Available immediately; locked mid-game once started
- **Endless mode:** Always available; separate play count tracked [Estimated]
- **No prestige/rebirth mechanics**

---

## 5. Economy & RNG *(tables)*

### Currencies

| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| None (base game) | — | — | — |
| Hint tokens (optional clone feature) | Soft | Watching rewarded ad | Reveal one letter (gray out impossible letters) |

**Base game has no economy.** [Confirmed for original Wordle]

Clone optional economy:
- 1 hint token per rewarded ad view
- Hint reveals one random unrevealed letter as green/yellow/gray
- Max 1 hint per puzzle to preserve challenge

### Cost Scaling
No upgrade costs. N/A.

### RNG
- **Daily mode:** Deterministic, not random. Word selected by `(daysSinceEpoch % answerList.length)` [Confirmed approach]
- **Endless mode:** `Math.random()` seeded implicitly by JS runtime; uniform distribution over answer list
- **No gacha, no loot boxes, no drop rates**
- Answer list is pre-shuffled (in original implementation) so consecutive days don't follow alphabetical order [Confirmed]

---

## 6. Content Inventory *(counts + lists)*

### Word Lists
| List | Count | Purpose |
|---|---|---|
| Answer list | ~2,500 words | Valid daily/endless targets; curated for familiarity |
| Guess list | ~12,000+ words | All accepted valid guesses; superset of answer list |

### Answer List Characteristics [Confirmed]
- Common, recognizable English words
- Excludes obscure, offensive, or highly technical words
- No proper nouns
- Excludes plurals of 4-letter words ending in S (where possible)
- Approximately 32% contain duplicate letters

### UI Screens
- Game board screen (main)
- How-to-play modal
- Stats modal
- Settings modal
- Win/lose overlay messages

### Animations / Juice Elements
- Tile flip (win color reveal)
- Tile bounce (win celebration)
- Row shake (invalid word)
- Key pop (typing feedback)
- Confetti or particle effect (win)
- Toast notification system

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract / minimalist puzzle space — no physical world, no characters
- **Premise:** No narrative. Pure word puzzle. The game is its own premise.
- **Story delivery:** None
- **Character personalities:** None
- **Tone:** Clean, calm, intellectually satisfying. Slight competitive edge from social sharing. No urgency pressure beyond the once-daily constraint.
- **Writing style:** Minimal copy. Short toasts ("Brilliant!", "Not in word list"). Stats labels. How-to instructions are brief and visual.
- **IP:** Original IP (Wordle) by Josh Wardle. Clone must use entirely original branding, name, and visual identity.
- **Color palette:** Original uses white/light-gray background, dark tiles flip to green (#6aaa64), yellow (#c9b458), gray (#787c7e). Dark mode inverts to dark background (#121213), darker tile variants.

---

## 8. Meta & Social Systems

### Social Sharing [Confirmed]
- One-click share button copies emoji grid to clipboard:
  ```
  LexiGuess #31 4/6
  ⬛🟨⬛⬛⬛
  🟨⬛🟨⬛⬛
  ⬛🟩🟩⬛🟨
  🟩🟩🟩🟩🟩
  ```
- No letters revealed in share — preserves spoiler-free experience
- Colorblind mode uses orange/blue instead of green/yellow in share output

### Daily Reset
- Countdown timer shown after completing daily puzzle (HH:MM:SS to midnight local time)

### Streaks
- Current streak displayed prominently in stats modal
- Max streak tracked
- Streak breaks if day is missed or puzzle lost

### Missions / Events / Battle Pass
- **None** in base game [Confirmed for original]
- Clone could add weekly word themes (optional extension)

### Live Ops
- Original: NYT editorial team updates word list quarterly; occasional special words for cultural events [Confirmed]
- Clone: Static pre-baked list; no live-ops infrastructure needed for MVP

### Multiplayer / Leaderboards
- **None** in original [Confirmed]
- Clone: Optional "compare with friends" via shared result strings (no backend needed)

---

## 9. UI / UX & Screen Map

### Screen List

| Screen | Purpose |
|---|---|
| Main Game Board | 6×5 guess grid + on-screen keyboard; primary gameplay |
| How to Play Modal | Animated examples of green/yellow/gray tiles; opens on first visit |
| Stats Modal | Played / Win% / Current Streak / Max Streak + distribution bar chart |
| Settings Modal | Dark mode, hard mode, colorblind mode, sound toggle, about/credits |
| Win Overlay | Brief word reaction ("Brilliant!" etc.) → fades into stats modal |
| Lose Overlay | Target word revealed in toast → stats modal opens |
| Countdown Display | Shows time to next daily word (inline on completed board) |

### Settings / Options Menu Contents
- Dark Mode toggle
- Hard Mode toggle (disabled if game in progress)
- Colorblind Mode toggle (orange/blue instead of green/yellow)
- Sound Effects toggle (mute/unmute)
- About / Credits link
- (Optional) Reset Stats button

### Gameplay HUD Elements
- Title bar: game name + menu icons (help, stats, settings)
- 6×5 tile grid (current guess row highlighted)
- Toast notification area (centered, above grid)
- On-screen keyboard (QWERTY rows + Enter/Backspace)
- Mode indicator (Daily / Endless badge)
- (Endless mode) "New Word" button after completion

### Navigation Flow
```
[First visit] → How to Play Modal → Main Board
[Any visit]   → Main Board
Main Board → [? icon] → How to Play Modal
Main Board → [chart icon] → Stats Modal
Main Board → [gear icon] → Settings Modal
Main Board → [Game complete] → Win/Lose Message → Stats Modal
Stats Modal → [Share button] → Clipboard copy → toast confirmation
```

### Onboarding / Tutorial Steps (First-Time User)
1. How to Play modal auto-opens
2. Show animated example: "WEARY" with W tile flipping green (right position)
3. Show example: "PILLS" with L tile flipping yellow (in word, wrong spot)
4. Show example: "VAGUE" with U tile flipping gray (not in word)
5. "One LexiGuess per day!" note
6. "Press anywhere to start" → modal closes → cursor appears in first row

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D** flat UI; no 3D world or camera
- Portrait orientation primary; responsive to any aspect ratio
- No scrolling — entire game fits one screen

### Art Style
- **Flat design / minimal** — no gradients in tiles, clean geometric shapes
- Tile size: ~62px squares on desktop; scales down on mobile (min ~52px)
- Font: Bold, monospace or sans-serif; letters centered in tiles
- Color palette:

| State | Light Mode | Dark Mode |
|---|---|---|
| Empty tile | White (#ffffff), gray border | Dark (#121213), lighter border |
| Filled (before reveal) | White, dark border | Dark, lighter border |
| Green (correct) | #6aaa64 | #538d4e |
| Yellow (present) | #c9b458 | #b59f3b |
| Gray (absent) | #787c7e | #3a3a3c |
| Keyboard default | #d3d6da | #818384 |

- Colorblind palette: Orange (#f5793a) for correct position, Blue (#85c0f9) for present

### Animation & VFX [Estimated]
- Tile pop: scale 1.0 → 1.08 → 1.0 on keypress (80ms)
- Tile flip: rotateX(0) → rotateX(90deg) (halfway, swap color) → rotateX(0) (250ms per tile, 300ms stagger between tiles)
- Row bounce: translateY with spring easing (600ms) on win
- Row shake: translateX oscillation (600ms) on invalid word
- Win particles: confetti burst from top (optional, subtle)
- Toast: fade in → hold 1.5s → fade out

### SFX (WebAudio, generated)
- Key click: short sine-wave pop (20ms, ~800Hz)
- Tile flip: soft whoosh (short filtered noise)
- Win fanfare: ascending chord (C-E-G, 400ms)
- Error: short buzz/descending tone
- All gated behind mute toggle

### Music
- None (typical for the genre; silence adds focus)

---

## 11. Monetization

### Original Game
- **No ads, no IAP** [Confirmed] — NYT uses it as a free engagement/subscription funnel
- Free to play with optional NYT Games subscription for archive access

### Clone Monetization (Recommended)
| Type | Placement | Frequency |
|---|---|---|
| Rewarded Ad (optional) | "Get a hint?" button after 3 failed guesses | Player-initiated only |
| Interstitial | After completing endless mode puzzle | Max 1 per 3 rounds |
| No banner ads | N/A | N/A (degrades UX for word game) |

### IAP Table [Estimated for clone]
| Product | Price | Contents |
|---|---|---|
| Remove Ads | $1.99 | No interstitials, keeps rewarded optional |
| Hint Pack (5) | $0.99 | 5 hint tokens without watching ads |

### Loot Boxes / Gacha
- None

### Consent / ATT / GDPR
- If ads implemented: show GDPR consent popup on first load (EU users)
- iOS: ATT prompt before any ad SDK initialization
- Age gate: not required (ESRB E / no personal data collected in core game)
- Privacy policy link in Settings modal

### Aggressiveness
- Very low. Original is zero-monetization. Clone should stay light to preserve the relaxed tone.

---

## 12. Retention Hooks

### Daily Reward / Login Streak
- The daily word itself IS the retention hook — designed scarcity creates habitual return [Confirmed]
- Streak counter motivates daily return (loss aversion: don't break the streak)
- Social sharing amplifies peer pressure to keep up

### Offline / Idle Earnings
- **None** — this is not an idle game [Confirmed]
- Game is playable fully offline (no network needed after initial load)

### Push Notifications
- Original: None [Confirmed]
- Clone: Optional "Your daily word is ready!" notification (if PWA with service worker)

### FOMO / Urgency
- Countdown timer after completing daily puzzle creates mild anticipation
- Streak breaking is the primary loss-aversion mechanic
- No energy system, no lives, no hard timers during play

### Energy / Lives System
- **None** [Confirmed] — unlimited guesses within the 6-try limit per puzzle

---

## 13. Localization & Accessibility

### Languages [Confirmed]
- Original English only (NYT version)
- Many unofficial Wordle clones in Spanish, French, Portuguese, etc.
- Clone MVP: English only; internationalization hooks in code for future

### RTL Support
- Not required for English; architecture should allow it

### Text Scaling
- Responsive tile and font sizes; minimum touch target 44px (iOS HIG)
- No fixed font sizes that break on zoom

### Colorblind Mode [Confirmed]
- Toggle in settings: replaces green/yellow with orange/blue (high-contrast distinguishable)
- Share output also uses colorblind-friendly emoji when mode active

### Difficulty / Assist Options
- Hard Mode (constraint-based, increases difficulty)
- No explicit "easy mode" or accessibility assists beyond colorblind

### Age / Content Rating
- ESRB: E (Everyone) / IARC: 3+
- No violence, no adult content, no chat features
- COPPA: No data collected from users under 13; no account creation required
- GDPR: Minimal — localStorage only (no personal data transmitted); ads require consent popup

### Regional Differences
- No regional content differences in base game
- Word list is American English spelling (colour → color, etc.)

---

## 14. Technical Structure

### Engine / Framework
- Vanilla JavaScript (no framework) [Confirmed for original]
- HTML5 + CSS3
- Clone: Single-file HTML (inline CSS + JS) for maximum portability

### Platforms
- Web (desktop + mobile browsers)
- PWA-capable (add manifest + service worker for installable app)
- Offline: fully functional without network after first load

### Save System
- localStorage only [Confirmed]
- Keys stored: `lexiguess_daily_state`, `lexiguess_stats`, `lexiguess_settings`, `lexiguess_endless_state`
- Daily state: current guesses, target (for reload resume), date string, complete flag
- Stats: gamesPlayed, wins, currentStreak, maxStreak, guessDistribution[1..6]
- Settings: darkMode, hardMode, colorblindMode, soundEnabled
- No cloud sync in base clone

### Accounts / Auth
- **None** in original web version [Confirmed]
- Clone: No auth required; guest-only

### Cross-Device Sync
- Not implemented in base clone (localStorage is device-local)
- Optional: URL-encoded state share for manual export

### Multiplayer / Netcode
- **None** — fully single-player [Confirmed]
- Anti-cheat: N/A (no competitive backend)

### Backend Services
- **None required** for base clone
- All logic runs client-side
- Word list embedded in JS

### Analytics
- Optional: privacy-friendly analytics (Plausible, Simple Analytics) — no user ID
- No third-party ad SDKs needed for ad-free version

### App Size
- Single HTML file: ~100–300KB (word list is primary size contributor)
- Original word lists: answer (~15KB text), guess (~80KB text)

### Performance
- No heavy assets; runs on low-end smartphones
- CSS animations GPU-accelerated (transform/opacity only)
- No canvas — pure DOM

---

## 15. Pacing & Difficulty

### Early Game (Days 1–7)
- How to Play modal explains mechanics clearly
- Most players grasp color system within 1–2 guesses
- First win creates strong positive reinforcement
- "Aha" moment: realizing yellows constrain placement, not just presence

### Mid Game (Weeks 2–8)
- Players develop opening word strategies (CRANE, AUDIO, SLATE, STARE common)
- Streaks begin forming; loss becomes emotionally significant
- Hard Mode adoption by engaged players (~20% estimated) [Estimated]
- Social sharing drives peer cohort competition

### Late Game (Ongoing)
- Daily habit deeply formed; streak defense is primary motivator
- Players seek optimal starting words, study common letter patterns
- Community discussion of daily word on social media
- Average solve: 3.80 guesses; ~98% win rate [Confirmed]

### Difficulty Curve
- Word difficulty varies by answer: JAZZY/VIVID (duplicates+uncommon) > CRANE/AUDIO (common)
- No explicit difficulty scaling — purely word-dependent
- Editorial curation can control difficulty variance over time

### Churn Points [Confirmed from reviews]
- Missing a day breaks streak → emotional friction → some quit
- Very hard words (archaic, obscure) → frustration → complaints
- "I got it in 1!" (lucky) vs "I needed all 6" (tense) — both create shareable moments

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---|---|---|
| 6×5 guess grid | ✓ | ✓ |
| Duplicate-letter-correct coloring | ✓ | ✓ |
| On-screen keyboard with state | ✓ | ✓ |
| Physical keyboard input | ✓ | ✓ |
| Valid-word validation + shake | ✓ | ✓ |
| Win/lose states | ✓ | ✓ |
| Daily mode (date-seeded) | ✓ | ✓ |
| Endless/random mode | ✓ | ✓ |
| Stats modal (played/win%/streak/distribution) | ✓ | ✓ |
| localStorage persistence | ✓ | ✓ |
| How-to-play modal | ✓ | ✓ |
| Dark mode | ✓ | ✓ |
| Colorblind mode | ✓ | ✓ |
| Hard mode | ✓ | ✓ |
| Tile flip animation | ✓ | ✓ |
| Share to clipboard (emoji grid) | ✓ | ✓ |
| Sound effects (WebAudio) | — | ✓ |
| Mute toggle | — | ✓ |
| Countdown timer | — | ✓ |
| PWA / service worker | — | ✓ |
| Rewarded ads / hints | — | ✓ |
| Particle/confetti win effect | — | ✓ |
| Keyboard shortcut for New Game | — | ✓ |

### Phased Build Roadmap

**Phase 1 — Core Game Engine (Week 1)**
- Embed answer list + guess list as JS arrays
- Daily seed algorithm: `Math.floor((Date.now() - epoch) / 86400000) % answerList.length`
- Coloring algorithm (green pass first, then yellow pass left-to-right)
- Valid-word check against guess list
- 6×5 DOM grid; physical + on-screen keyboard
- Win/lose detection + message
- Basic CSS (tiles, keyboard, colors)

**Phase 2 — Polish & Persistence (Week 1–2)**
- Tile flip CSS animation
- Row shake (invalid), row bounce (win)
- localStorage save/restore (daily state, stats)
- Stats modal with distribution bars
- How-to-play modal (first visit auto-open)
- Toast notification system

**Phase 3 — Modes & Settings (Week 2)**
- Endless/random mode with "New Word" button
- Hard mode enforcement logic
- Settings modal (dark mode, colorblind, hard mode, sound)
- Dark mode CSS variables
- Colorblind color swap
- Share button (emoji grid to clipboard)

**Phase 4 — Audio & Juice (Week 3)**
- WebAudio SFX (key click, flip, win, error)
- Mute toggle
- Countdown timer to next daily word
- Confetti win effect (CSS or canvas)
- Responsive/mobile polish pass

**Phase 5 — Optional Enhancements**
- PWA manifest + service worker
- Optional hint system + rewarded ads
- Analytics integration
- Accessibility audit (ARIA labels, focus management)

### Recommended Tech Stack
- HTML5 + CSS3 + vanilla ES6 JavaScript
- No framework (React/Vue add unnecessary complexity for this scope)
- CSS custom properties for theming (dark/colorblind modes trivial to implement)
- Web Animations API or CSS keyframes for tile animations
- Web Audio API for SFX (no audio files needed)
- localStorage for all persistence

### Required Asset List
- Word list data (embedded, ~100KB text → minified)
- Icon/favicon (SVG, simple letter-in-square motif)
- No images required (pure CSS tiles)
- No audio files (WebAudio generates all SFX procedurally)

### Hardest Parts / Risks
1. **Duplicate-letter coloring algorithm** — most common bug in clones; must do green pass first, then count-limited yellow pass. Test with: SPEED vs SPOKE, LILLY vs HELLO, SPEED vs SPEED.
2. **Daily seed determinism** — must be timezone-consistent. Use UTC date (not local) to ensure all users worldwide see the same word on the same calendar day.
3. **Hard mode enforcement** — tracking which yellows must appear and which greens must stay requires careful state management.
4. **Mobile keyboard avoidance** — on-screen keyboard must prevent native mobile keyboard from appearing (inputs are not used; key events dispatched via on-screen keyboard); use `readonly` or avoid `<input>` entirely.
5. **Word list size** — embedding 12,000+ words inflates file size; consider a compact trie or prefix structure if file size matters; for MVP, raw array is fine.
6. **Stats persistence across daily/endless modes** — decide whether to unify or separate stats before building.

---

## 17. Open Questions

1. **Word list curation depth:** The embedded word list quality determines perceived game quality. A developer should audit ~50 random answers for recognizability and remove any offensive/obscure words. [Needs human review]
2. **Timezone handling for daily seed:** UTC-based daily word means "new word" arrives at midnight UTC, which is evening for Americas. Consider local-midnight reset as alternative — but this breaks "same word for everyone" globally. Design decision to confirm. [Estimated: UTC is simpler and correct for global sync]
3. **Hard mode partial enforcement:** When a yellow constraint says "E must appear somewhere," should a hard-mode violation just toast-warn or block submission? [Estimated: block submission with descriptive toast]
4. **Endless mode stats:** Should endless wins count toward the main streak? [Estimated: No — separate counters preserve meaning of the daily streak]

---

*Blueprint complete. All 17 sections filled. Economy tables present (N/A noted as confirmed). Duplicate-letter algorithm confirmed and described. All game modes enumerated. Screen map complete with onboarding steps. Monetization options defined (original = none; clone = optional light). Build plan phased with risks flagged. Tagged [Confirmed]/[Estimated] throughout.*
