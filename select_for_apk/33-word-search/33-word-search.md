# Word Search — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Word Search is a classic pen-and-paper puzzle game digitized into an interactive browser/app
experience. A grid of letters hides a list of themed words in any combination of 8 directions
(horizontal, vertical, diagonal — forwards and backwards). The player scans the grid, drags
across the hidden word's letters to select it, and the game confirms the find, crosses the word
off the list, and highlights it in the grid. The core appeal is relaxed, satisfying pattern
recognition with a clear win state: find every word, stop the clock.

**Quick facts:**
- Genre: Word Puzzle / Casual
- Developer (reference): Multiple publishers — word search is a public-domain puzzle format
- Platforms: Web (primary), iOS, Android
- Session length: 3–15 min (Easy), 10–30 min (Medium), 20–60 min (Hard) [Estimated]
- Age/content rating: ESRB E (Everyone); PEGI 3 — suitable all ages; no COPPA/GDPR-K concerns for general builds [Estimated]
- Monetization model (typical): Ad-supported free + optional ad-removal IAP; this clone is ad-free single-player [Confirmed — design decision]
- Comparable titles: Word Search by PeopleFun, Word Search Pro, Words of Wonders, A-Z Word Search

---

## 2. Core Loops

- **30-second loop:** Scan the grid visually → spot a candidate word pattern → drag across letters → hear/see confirmation → cross word off list → scan again.
- **Session loop:** Choose theme + difficulty → puzzle generates → find all N words before time runs out (or just for personal best time) → win screen shows time + new-record flag → choose new puzzle or replay.
- **Meta loop:** Compare personal best times across theme/difficulty combos stored in localStorage → unlock sense of mastery by beating previous records → curiosity about other themed word banks drives replays.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
- **Grid:** Letter grid of fixed size per difficulty (Easy 10×10, Medium 14×14, Hard 18×18). [Estimated — based on genre norms]
- **Word placement:** Generator places each word in one of up to 8 directions; difficulty restricts which directions are available (see Difficulty section).
- **Fill:** Remaining cells filled with random A–Z letters weighted toward common vowels/consonants to reduce accidental false words.
- **Selection:** Player drags from first to last letter of a word in a straight line. Diagonal, horizontal, and vertical lines all valid.
- **Validation:** On drag release, check if selected cell sequence (forward or reverse) matches any unfound word. If yes → found; if no → silently reset highlight.
- **Found state:** Matched word gets a semi-transparent coloured highlight overlay on its cells; word in the word list gets a strikethrough + checkmark.
- **Win condition:** All words found. Trigger win overlay with time, best-time comparison, confetti, and win sound.
- **No lose condition:** Timer counts up (not a countdown); player can take as long as they want.

### Game Modes
| Mode | Description |
|------|-------------|
| Standard Puzzle | Find all words; timer counts up; win when complete |
| New Puzzle | Generate fresh puzzle, same theme/difficulty |
| Theme Select | Pick from 6 themed word banks |
| Difficulty Select | Easy / Medium / Hard — changes grid size and allowed directions |

### Controls
| Action | Desktop | Mobile |
|--------|---------|--------|
| Start selection | Mouse down on cell | Touch start on cell |
| Extend selection | Mouse move (held) | Touch move |
| Submit selection | Mouse up | Touch end |
| Cancel selection | Mouse leave grid | — |
| New puzzle | Button click | Button tap |
| Mute | Button click | Button tap |

- Screen orientation: Portrait primary; landscape supported via responsive CSS grid.
- Input: Drag (mouse/touch) only — no keyboard letter-clicking needed; simpler UX.

### Difficulty Modes
| Difficulty | Grid | Words | Directions | Backwards allowed | Approx. time |
|------------|------|-------|------------|-------------------|--------------|
| Easy | 10×10 | 8 | H + V only | No | 3–8 min |
| Medium | 14×14 | 12 | H + V + Diagonal | No | 8–15 min |
| Hard | 18×18 | 16 | All 8 directions | Yes | 15–40 min |

[Estimated — consistent with industry-standard difficulty tiers]

### Win / Fail States
- **Win:** All words found → overlay appears; time recorded vs. best time.
- **No fail:** Player cannot lose; puzzle stays open indefinitely.
- **Stuck help:** No built-in hint in MVP; optional stretch: "Show a word" highlight one random unfound word for 2 seconds.

### Feedback Systems
- **Visual:** Live highlight of drag path (blue tint); word found = persistent coloured stripe; word list strikethrough.
- **Audio:** Soft tick on each cell entered during drag; chord/chime on word found; triumphant fanfare on win; all via Web Audio API.
- **Haptic:** Not applicable (web); mobile browsers can use `navigator.vibrate([30])` on find. [Estimated]

---

## 4. Progression

- **No XP or leveling.** The game is stateless per puzzle.
- **Soft progression:** Best times per theme × difficulty (6 themes × 3 difficulties = 18 records) stored in localStorage. Personal improvement is the progression hook.
- **Content unlock:** All themes unlocked from start; no gating.
- **Difficulty curve:** Easy → Medium → Hard available at any time; no forced sequencing.

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| Time (seconds) | Soft score metric | Playing puzzles | N/A — lower is better |

No in-game currency, premium currency, or economy. This is a pure skill/time game. [Confirmed — design decision]

### RNG
| RNG Element | Mechanism | Notes |
|-------------|-----------|-------|
| Word placement | Shuffle candidate (row, col, dir) triples, attempt each, backtrack if blocked | Each puzzle is uniquely randomized |
| Fill letters | Random A–Z, slightly weighted (ETAOIN SHRDLU higher frequency) | Reduces accidental letter clusters |
| Word order in list | Shuffled display order so word length doesn't hint at position | [Estimated] |

### Cost Scaling
No cost scaling — no economy. Puzzle generation is O(n·k) where n = grid cells, k = words.

### Drop Rates
None — no loot, no gacha.

---

## 6. Content Inventory *(counts + lists)*

### Themed Word Banks (6 themes, 20 words each — puzzle draws 8/12/16 per difficulty)

| Theme | Sample Words |
|-------|-------------|
| **Animals** | ELEPHANT, DOLPHIN, PENGUIN, JAGUAR, GIRAFFE, COBRA, PARROT, FALCON, SALMON, GORILLA, HAMSTER, COYOTE, PANTHER, SPARROW, WALRUS, HEDGEHOG, NARWHAL, PUFFIN, BISON, LEMUR |
| **Fruits** | MANGO, PAPAYA, GUAVA, LYCHEE, COCONUT, KUMQUAT, DURIAN, APRICOT, PLUM, PEACH, MELON, CHERRY, LEMON, LIME, ORANGE, KIWI, BANANA, GRAPE, PEAR, FIG |
| **Space** | GALAXY, NEBULA, COMET, ASTEROID, QUASAR, PULSAR, METEOR, ECLIPSE, SATURN, VENUS, JUPITER, ORBIT, PHOTON, COSMOS, LUNAR, SOLAR, NOVA, ROCKET, CRATER, HORIZON |
| **Ocean** | CORAL, TSUNAMI, CURRENT, LAGOON, ABYSSAL, PLANKTON, KELP, MANATEE, SEAHORSE, NAUTILUS, BARNACLE, OCTOPUS, SQUID, JELLYFISH, DOLPHIN, TIDAL, SHORE, DRIFTWOOD, REEF, BRINE |
| **Sports** | SOCCER, TENNIS, HOCKEY, CRICKET, RUGBY, SURFING, ARCHERY, FENCING, ROWING, BOXING, CYCLING, DIVING, KARATE, SKATING, SPRINT, HURDLES, JAVELIN, DISCUS, TRIATHLON, POLO |
| **Nature** | THUNDER, GLACIER, CANYON, VOLCANO, TUNDRA, PRAIRIE, SWAMP, FOREST, MEADOW, ESTUARY, PLATEAU, GEYSER, AVALANCHE, WATERFALL, DUNE, FJORD, LAGOON, BASIN, RIDGE, MARSH |

### Grid Sizes (3 difficulties × 1 layout each)
- Easy: 10×10 = 100 cells
- Medium: 14×14 = 196 cells
- Hard: 18×18 = 324 cells

### Total Possible Puzzles
Effectively infinite (random placement each time) across 18 theme/difficulty combos.

---

## 7. Theme, Narrative & Tone

- **Setting:** Puzzle board interface with clean, modern design. Each theme carries a matching palette and icon (e.g., deep blue for Ocean, star-field for Space).
- **Premise:** No story. Pure puzzle — the satisfaction is the solve.
- **Tone:** Calm, focused, satisfying. Not competitive (no leaderboards vs. others). Relaxing but mentally engaging — "coffee table puzzle" feel.
- **Visual theme:** Flat design with light-colored cells, accent colors per theme, smooth animations.
- **Writing style:** Minimal UI copy, friendly and direct. ("Great find!", "Puzzle complete!", "New best time!").
- **IP:** No licensed IP. Word banks are public-domain vocabulary.

---

## 8. Meta & Social Systems

- **Daily streak:** Not present in MVP. [Design decision]
- **Achievements:** Not in MVP; could add "First Hard Puzzle Solved", "Speed Demon < 60s Easy". [Estimated stretch]
- **Leaderboards:** None (no server). Best times are local only.
- **Social:** No sharing, guilds, or multiplayer. Word search is inherently solo.
- **Live ops:** None needed — static word banks embedded in HTML.
- **Events:** Themed puzzle of the day could be a stretch goal (requires no server if date-seeded RNG).

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| **Main / Game Screen** | Primary gameplay view: theme picker, difficulty picker, grid, word list, HUD, new puzzle button |
| **Win Overlay** | Modal: time, personal best, confetti, "New Puzzle" + "Same Puzzle Again" buttons |
| **How-To Overlay** | Instructions panel: drag to select, directions, scoring; dismissible |
| **Settings / HUD Controls** | Inline: mute toggle, how-to button, timer display |

*(No login screen, no shop screen — single-screen app with overlays)*

### Gameplay HUD
- Theme name (top-left)
- Difficulty badge (Easy/Medium/Hard)
- Timer: MM:SS counting up
- Found counter: "5 / 12 words"
- Mute toggle button
- "?" How-to button
- New Puzzle button

### Word List Panel
- All N words listed; each 1.5rem bold text
- Found words: strikethrough + dimmed + checkmark icon
- Words sorted alphabetically for easy scanning
- Scrollable on small screens

### Navigation Flow
```
App Load
  └─► Main Screen (default: Animals / Easy)
        ├─► Theme Selector (dropdown/tab row)
        ├─► Difficulty Selector (Easy / Medium / Hard tabs)
        ├─► Grid (drag to play)
        ├─► Win Overlay ──► New Puzzle ──► Main Screen (new puzzle)
        ├─► How-To Overlay (modal, close button)
        └─► New Puzzle Button ──► Main Screen (new puzzle, same theme/difficulty)
```

### Onboarding / Tutorial (First Launch)
1. Game loads with a small "How to Play" modal on first visit (localStorage flag).
2. Modal shows: animated hand dragging across 3 cells + text "Drag from first to last letter of any word in the list."
3. "Got it!" button dismisses and starts puzzle.
4. No further hand-holding — the grid + word list is self-explanatory.

### Settings / Options
- Mute / unmute SFX (persisted to localStorage)
- No language select in MVP
- No theme/color mode in MVP

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D flat
- **Camera/perspective:** Direct top-down grid view; no camera — just HTML/CSS layout
- **Orientation:** Portrait-first responsive; landscape works via CSS grid auto-sizing
- **Art style:** Flat design, rounded corners, clean sans-serif font (system-ui or Nunito-style)
- **Palette:**
  - Animals: warm amber + forest green
  - Fruits: bright coral + citrus yellow
  - Space: deep navy + electric purple + star-white
  - Ocean: teal + aqua + seafoam
  - Sports: bold red + charcoal + bright white
  - Nature: sage green + earth brown + sky blue
- **Cells:** White/off-white squares with subtle border; selected path: mid-opacity colored overlay; found word: persistent stripe (each word gets a unique accent color from a palette of 8)
- **Animation:** Smooth drag highlight; word-found pulse animation on list item; win confetti (CSS keyframes + JS canvas or pure CSS)
- **VFX:** Cell highlight on drag, burst ripple on word found, confetti shower on win
- **SFX (Web Audio, no files):**
  - Drag tick: short 800Hz blip, 20ms
  - Word found: ascending 3-note chord (C5, E5, G5), 300ms
  - Win: fanfare (C5 E5 G5 C6 arpeggiated), 800ms
  - All sounds generated via OscillatorNode + GainNode
- **Mute toggle:** Single button; state saved to localStorage
- **Juice factor:** Satisfying stutter-free drag, immediate color flash on find, list item strikethrough animation — all contribute to the "pop" feel

---

## 11. Monetization

**This clone is ad-free with no IAP.** [Design decision for pure gameplay experience]

- No banners, interstitials, rewarded ads, or consent/ATT flows needed.
- No IAP, subscriptions, or battle pass.
- No GDPR/CMP pop-up required (no tracking, no ads, no third-party SDKs).
- No age gate required (content is suitable for all ages; no data collected).

If monetization were added later:
| Ad Type | Suggested Placement | Frequency |
|---------|---------------------|-----------|
| Rewarded | "Show a hint" button | On demand |
| Interstitial | Between puzzles (after win) | Every 3rd completion |
| Banner | Below word list | Continuous |

---

## 12. Retention Hooks

- **Personal best times** per 18 combos (localStorage) — primary re-engagement driver.
- **Theme variety** — 6 different themes keep content fresh.
- **No energy/lives system** — player can play endlessly without friction.
- **No offline earnings** — not applicable (active play only; no idle mechanic).
- **No push notifications** — web app; could add Web Push in a future version.
- **FOMO:** None deliberately designed; relaxed experience.
- **Endless replayability:** Procedurally generated puzzles — same theme/difficulty never repeats exactly.

---

## 13. Localization & Accessibility

### Localization
- MVP: English only. [Estimated]
- Word banks are in English; a future version could swap in localized word lists.
- No RTL support needed for English-only build.
- Font uses system-ui fallback chain for broad Unicode support.

### Accessibility
- **Text scaling:** Cells scale via CSS clamp/vw units; word list uses rem-based type.
- **Colorblind mode:** Each found word gets both a color stripe AND a unique letter marker (1, 2, 3…) on the word list for non-color discrimination. [Estimated]
- **Touch targets:** Cells sized minimum 36×36px on Easy, proportionally smaller on Hard but touch dragging is forgiving (hit-test includes cell center).
- **Keyboard:** Not supported in MVP (drag-only game); could add keyboard selection in v2.
- **Screen reader:** Grid cells have `aria-label` attributes with their letter; word list items have `aria-live` for found announcements. [Estimated]
- **Reduced motion:** `prefers-reduced-motion` media query disables confetti animation.

### Age/Content Rating
- ESRB: E (Everyone) [Estimated]
- PEGI: 3 [Estimated]
- No violence, no mature content, no in-game purchases — suitable for children.
- COPPA compliance: No data collected, no accounts, no analytics → fully compliant by default.

---

## 14. Technical Structure

- **Engine/Framework:** Vanilla JavaScript (ES6+), HTML5, CSS3 — no build step, no frameworks.
- **Rendering:** DOM-based grid (CSS Grid layout); no Canvas needed for core gameplay. Canvas optional for confetti win effect.
- **Platform:** Web — runs in any modern browser, offline (no network requests).
- **Distribution:** Single `.html` file with inline `<style>` and `<script>`.
- **Save system:** localStorage keys:
  - `ws_bestTimes` — JSON object: `{ "Animals_Easy": 120, "Space_Hard": 650, ... }`
  - `ws_muted` — `"1"` or `"0"`
  - `ws_tutorialSeen` — `"1"` if first-launch modal was dismissed
- **Accounts/Auth:** None.
- **Cross-device sync:** None (local only).
- **Multiplayer:** None.
- **Anti-cheat:** N/A (single-player, no server).
- **Analytics:** None in this build.
- **Third-party SDKs:** None.
- **App size:** < 50 KB (pure HTML/CSS/JS, no images/fonts fetched).
- **Performance:** Grid render < 16ms on modern devices; word placement algorithm runs in < 100ms even for 18×18 grid. Generator capped at 100 retries per word, 10 full-puzzle retries.
- **Browser support:** Chrome 80+, Firefox 80+, Safari 14+, Edge 80+ (all ES6+ with CSS Grid).

---

## 15. Pacing & Difficulty

### Early Game (Easy — first puzzle)
- 10×10 grid, 8 words, horizontal/vertical only, no backwards.
- Player can scan methodically row by row.
- First word typically found within 15 seconds — immediate positive feedback.
- Tutorial modal explains drag mechanic on first visit.
- Expected first-puzzle time: 3–8 minutes. [Estimated]

### Mid Game (Medium — returning player)
- 14×14 grid, 12 words, diagonals added, no backwards.
- Diagonals significantly increase scan difficulty.
- Player develops diagonal scanning strategy.
- Expected puzzle time: 8–15 minutes. [Estimated]

### Late Game (Hard — experienced player)
- 18×18 grid, 16 words, all 8 directions including backwards.
- Backwards words break pattern-recognition heuristics; player must letter-match.
- The large grid requires zoomed view on mobile.
- Expected puzzle time: 15–40 minutes. [Estimated]

### Difficulty Curve
```
Easy     ■■□□□□□□  (straightforward, builds confidence)
Medium   ■■■■□□□□  (diagonals force new strategy)
Hard     ■■■■■■■■  (all directions, cognitive challenge)
```

### Milestone Moments
1. First word found → confirmation chime + strikethrough (dopamine hit)
2. Halfway mark → progress visible in word list
3. Last word found → win overlay + fanfare + confetti (peak satisfaction)
4. New personal best → highlighted record display

### Churn Points [Estimated]
- Hard difficulty without any diagonal words is anticlimactic → diagonals must be present in Hard
- Very long words in a small grid can block generation → minimum word length capped relative to grid size
- No hint system can frustrate players stuck on final word → consider a 30s "pulse" hint as stretch feature

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---------|-----|------|
| Procedural grid generator (8 dirs) | ✓ | ✓ |
| 3 difficulty levels | ✓ | ✓ |
| 6 themed word banks | ✓ | ✓ |
| Drag selection (mouse + touch) | ✓ | ✓ |
| Word found detection + highlight | ✓ | ✓ |
| Word list with strikethrough | ✓ | ✓ |
| Timer (count-up) | ✓ | ✓ |
| Win overlay + confetti | ✓ | ✓ |
| Best time persistence (localStorage) | ✓ | ✓ |
| Web Audio SFX (generated) | ✓ | ✓ |
| Mute toggle | ✓ | ✓ |
| First-launch how-to modal | ✓ | ✓ |
| Responsive / mobile-first | ✓ | ✓ |
| Keyboard selection | — | ✓ |
| Hint system ("show word") | — | ✓ |
| More themes (10+) | — | ✓ |
| Date-seeded daily puzzle | — | ✓ |
| Colorblind mode | — | ✓ |
| Share result | — | ✓ |

### Phased Roadmap

**Phase 1 — Core Engine (Day 1)**
1. Data structures: grid array, word list, placed-word registry
2. Word placement algorithm: attempt all (row, col, dir) combos shuffled; skip on collision except same letter; backtrack if all fail; cap at 100 retries per word; if all words can't place after 10 full resets → reduce word count by 1 and retry
3. Fill remaining cells with random letters
4. Render grid as CSS Grid of `<div>` cells

**Phase 2 — Selection & Detection (Day 1–2)**
5. Mouse/touch event handlers: `mousedown`, `mousemove`, `mouseup`, `touchstart`, `touchmove`, `touchend`
6. Determine drag direction vector; reject non-straight paths
7. Build cell sequence from start to end along that vector
8. On release: check sequence (and reverse) against unfound word list
9. Mark found: highlight cells, strikethrough list item

**Phase 3 — UI & Polish (Day 2)**
10. Theme selector (tab bar)
11. Difficulty selector (Easy/Medium/Hard tabs)
12. HUD: timer, found counter, mute
13. Win overlay with time + best time comparison
14. Confetti animation
15. How-to modal (first launch)

**Phase 4 — Audio & Persistence (Day 2–3)**
16. Web Audio SFX: drag tick, found chord, win fanfare
17. Mute toggle + localStorage persistence
18. Best times localStorage read/write

**Phase 5 — QA & Polish (Day 3)**
19. Test all 18 theme/difficulty combos for generator correctness
20. Test touch drag on real mobile device
21. Verify no console errors
22. Test generator retry logic (force edge cases with many long words)
23. Confirm </html> at EOF

### Recommended Tech Stack
- HTML5 + CSS3 + Vanilla ES6+ JavaScript
- No frameworks, no build tools
- CSS Custom Properties for theme palettes
- CSS Grid for letter grid layout
- Web Audio API for SFX
- localStorage for persistence

### Required Asset List
- No image files (CSS-only UI)
- No audio files (Web Audio generated)
- No fonts fetched (system-ui stack)
- Word banks: 6 × 20 words = 120 words (inline JS array)

### Hardest Parts / Risks
1. **Generator reliability:** Placing 16 words in an 18×18 grid in all 8 directions with no bad overlaps requires robust retry logic. Risk: infinite loop. Mitigation: hard cap retries, reduce word count as fallback.
2. **Touch drag on mobile:** `touchmove` requires `preventDefault()` to stop page scroll, but this blocks passive listeners warning. Fix: use `{ passive: false }` on touch event registration.
3. **Diagonal line detection:** Verifying a drag path is perfectly diagonal (equal Δrow and Δcol) requires floating-point-safe integer checks. Off-by-one errors in cell sequence building cause false negatives.
4. **Responsive grid sizing:** 18×18 grid must fit on a 320px-wide phone screen. CSS `clamp()` on cell size; minimum font size floor to remain readable.
5. **Reverse word matching:** "DRAWER" backwards is "REWARD" — the search must try both orientations on every drag. Easy to miss and causes frustrating false negatives on Hard mode.

---

## 17. Open Questions

1. **Hint system threshold:** At what elapsed time (if any) should the game offer a hint? Needs playtesting — 5 minutes without a new find might be the right trigger. [Needs playtesting]
2. **Minimum word length per difficulty:** Should Easy cap words at 6 letters to prevent hard-to-see short words? Recommend minimum 4, maximum 8 for Easy as a starting point. [Needs tuning]
3. **Mobile cell size floor on Hard:** 18×18 on a 360px screen = 20px cells — borderline readable. Consider limiting Hard to landscape only on very small screens, or offering a "zoom" pan-and-scan mode. [Needs device testing]
