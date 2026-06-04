# Sudoku — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Sudoku is the world's most popular logic-based number puzzle. The player fills a 9×9 grid divided into nine 3×3 boxes so that every row, column, and box contains the digits 1–9 exactly once, with no repeats. Appeal comes from the satisfying interplay of deduction and pattern recognition, the absence of math (it's purely logic), and the clean "one right answer" guarantee. The game is completely offline and single-player, with a typical session of 5–30 minutes depending on difficulty.

**Quick facts:**
- **Developer/Genre:** Logic puzzle / Number placement (genre-native, not a single developer)
- **Platforms:** Web, iOS, Android, print — omnipresent
- **Release:** 1979 (Howard Garns, Number Place); global popularity via Nikoli 1986
- **Rating:** IARC 3+ / Everyone; no age-gate concerns
- **Monetization model (clone target):** Ad-supported free with optional IAP remove-ads; or pure free

---

## 2. Core Loops

- **30-second loop:** Player selects an empty cell → taps/types a digit (or pencil note) → game instantly highlights conflicts or confirms placement → player scans for next deduction.
- **Session loop:** Open app → select difficulty → new puzzle generated with unique solution → solve cells using logic (naked singles, scanning, elimination) → use pencil marks for candidates → resolve all 81 cells → win screen shows time + mistakes → optionally start new game.
- **Meta loop:** Improve personal best times per difficulty; unlock "streak" badges; progress from Easy to Hard mastery over days/weeks; return daily for new puzzle.

---

## 3. Mechanics, Controls & Game States

### Core Rules [Confirmed]
- 9×9 grid, divided into nine 3×3 boxes.
- Each row, column, and 3×3 box must contain each digit 1–9 exactly once.
- "Givens" (pre-filled cells) are locked and cannot be edited.
- Player fills only empty cells.
- Puzzle has exactly **one** unique solution.

### Core Verbs
- **Select** a cell (tap/click)
- **Enter** a digit (1–9) via on-screen pad or keyboard
- **Erase** a digit or note
- **Toggle pencil/notes mode** — place small candidate digits instead of a final answer
- **Hint** — reveal one correct cell value
- **Check** — highlight all incorrect cells
- **Solve** — auto-complete the board (optional, forfeits puzzle)

### Game Modes [Confirmed]
| Mode | Description |
|------|-------------|
| Easy | ~45–50 givens; solvable by naked singles only |
| Medium | ~35–44 givens; requires light hidden singles / box-line |
| Hard | ~26–34 givens; requires pairs, pointing pairs, intersections |
| Daily Challenge | [Estimated] Seeded puzzle, same for all players on a date |

### Input Scheme
- **Desktop:** Click cell → keyboard digit (1–9), Delete/Backspace to erase, Arrow keys navigate, N or P toggles notes mode
- **Mobile:** Tap cell → tap on-screen number pad (1–9, Erase, Notes toggle); supports touch with large tap targets

### Screen Orientation
- **Portrait** on mobile (primary); works landscape too. Grid expands to fill width.

### Win / Lose / Fail States [Confirmed]
- **Win:** All 81 cells filled with digits matching the unique solution → win overlay with time and mistake count.
- **Mistake:** Player enters a digit that conflicts with row/column/box → mistake counter increments (max 3 mistakes → soft "game over" warning, not forced end). [Estimated: mistake limit optional, configurable]
- **Fail / Game Over:** [Estimated] At 3 mistakes, show a notice; player may continue anyway or start new game.
- **No lives lost / no currency lost** — casual-friendly, infinite retry.

### Conflict Highlighting [Confirmed]
- Cells in the same row, column, or box as the selected cell are lightly tinted (peer highlight).
- Any cell with the same digit as the selected cell is highlighted differently (same-number highlight).
- Incorrect entries (mismatched from solution) shown in red on input or when Check is pressed.

### Difficulty AI
- No AI opponent. Difficulty is controlled by the number and placement of givens, and the solving techniques required [Confirmed].
- Generator ensures unique solution via backtracking uniqueness check after each removal [Confirmed].

### Feedback Systems
- Visual: cell pop animation on correct entry; shake + red flash on conflict/error.
- Audio (WebAudio): soft "tick" on placement, "buzz" on error, ascending chime on win.
- Haptic: [Estimated] Short vibration on mobile via Vibration API on error; longer on win.

---

## 4. Progression

### Difficulty Unlock [Estimated]
All difficulties unlocked from start; no gating required.

### Personal Best / Statistics [Confirmed]
- Best time per difficulty stored locally.
- Mistake count tracked per game.
- Win streak tracked (optional).

### Upgrade/Prestige Systems
- None. Sudoku is stateless between puzzles — no XP, no upgrade tree.
- **Skill progression** is entirely player-skill based (faster solve times, fewer hints/mistakes).

### Gating
- **Hints** are limited (e.g., 3 per puzzle free; more via ad watch or IAP) [Estimated].
- No paywalls on puzzle access.

---

## 5. Economy & RNG *(tables)*

### Currencies

| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| Hints | Soft consumable | Start with 3/puzzle; earn via ad reward or IAP | Reveal one correct cell |
| (No hard currency in base design) | — | — | — |

### Cost-Scaling
- No upgrade economy. Hints replenish to 3 at each new game [Estimated].
- IAP: "Remove Ads" one-time $1.99–$2.99; "Unlimited Hints" $0.99 or bundle [Estimated].

### RNG / Drop Rates
- **Puzzle generation RNG:** Full valid 9×9 solution built by backtracking with randomized digit order (Fisher-Yates shuffled candidate list per cell). Then cells removed symmetrically (180° point symmetry, common convention) until target givens count reached, with uniqueness check after each removal [Confirmed].
- **No loot, gacha, or drop tables.** Pure logic puzzle — no RNG during play.

### Key Tuning Numbers [Estimated]

| Difficulty | Target Givens | Cells Removed | Typical Solve Time |
|------------|--------------|----------------|-------------------|
| Easy | 46–50 | 31–35 | 5–10 min |
| Medium | 36–45 | 36–45 | 10–20 min |
| Hard | 26–35 | 46–55 | 20–45 min |

---

## 6. Content Inventory *(counts + lists)*

### Puzzles
- **Procedurally generated** — effectively infinite, always unique solution [Confirmed].
- Seeded daily puzzle (optional): 1 per day, same seed for all players.

### Grid Elements
- 81 cells (9×9)
- 9 rows, 9 columns, 9 boxes
- Digits 1–9

### UI Elements / Screens
- Difficulty selector, New Game button, HUD, 9×9 grid, number pad (1–9 + Erase + Notes), Notes toggle, Hint button, Check button, Solve button, Mute button, Win overlay.

### No Characters, Enemies, Items
Sudoku has no character system, no enemies, no items, no collectibles.

---

## 7. Theme, Narrative & Tone

### Setting & Visual Theme [Estimated]
- Clean, minimal, magazine-quality aesthetic. White or off-white grid on neutral background.
- Strong 3×3 box borders (2px+), thinner cell borders.
- Palette options: Classic white/black; Dark mode (charcoal + cream); Warm paper tone.

### Premise
- No story or narrative. The game is the puzzle.
- Tagline examples: "Pure Logic. One Answer." / "Train Your Brain."

### Tone [Confirmed]
- Calm, focused, satisfying. Not competitive or stressful.
- Rewards patience and careful thinking.
- Font: clean sans-serif (system font or bundled); digits large and legible.

---

## 8. Meta & Social Systems

### Daily Puzzle [Estimated]
- One seeded puzzle per day; timer tracked; shareable result (like Wordle-style).

### Achievements [Estimated]
| Achievement | Trigger |
|-------------|---------|
| First Solve | Complete first puzzle |
| Speedster | Solve Easy in under 3 min |
| No Mistakes | Solve any puzzle with 0 mistakes |
| Hard Master | Solve Hard puzzle |
| 7-Day Streak | Solve puzzle 7 days in a row |

### Live-Ops Cadence
- Daily puzzle is the only live-ops requirement [Estimated].
- No seasons, no battle pass needed for a web clone.

### Multiplayer / Social
- None in core version. Leaderboard for daily challenge optional.
- Share result: "I solved today's Hard puzzle in 18:42 with 0 mistakes!"

### Decorations / Base Building
- None.

---

## 9. UI / UX & Screen Map

### Screen List

| Screen | Purpose |
|--------|---------|
| Loading / Splash | Brief brand flash, load assets |
| Main Menu | Difficulty selector, New Game, Best Times, Settings |
| Game Screen | Core 9×9 grid, HUD, number pad, action buttons |
| Pause / Menu Overlay | Resume, New Game, Settings, Quit |
| Win Overlay | Celebration, time, mistakes, New Game, Share |
| Settings Screen | Sound on/off, Theme (light/dark), Clear data |
| How to Play | Rules, pencil-mark tutorial |

### Settings / Options Menu Contents
- Sound: On / Off toggle
- Theme: Light / Dark
- Highlight conflicts: On / Off
- Show mistake count: On / Off
- Clear saved progress
- About / Credits

### Gameplay HUD Elements
- Timer (MM:SS) — top left or center
- Mistake counter (e.g., "Mistakes: 2/3") — top right
- Difficulty label — below timer
- Number pad — bottom of screen
- Notes toggle button (pencil icon)
- Hint button (lightbulb icon)
- Check button (checkmark icon)
- New Game / Menu button

### Navigation Flow
```
Main Menu → [Select Difficulty] → Game Screen
Game Screen → [Menu] → Pause Overlay → Main Menu
Game Screen → [Win] → Win Overlay → Game Screen (new) / Main Menu
```

### Onboarding / Tutorial Steps [Estimated]
1. First launch: brief tooltip — "Tap a cell to select it."
2. Number pad highlighted: "Tap a number to fill the cell."
3. Conflict shown: "Red means a conflict — try another number."
4. Pencil icon pulsed: "Use pencil mode to jot down candidates."
5. "That's it! Fill all 81 cells to win. Good luck!"

(Tutorial dismissable, never shown again.)

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- 2D, flat top-down grid; no camera movement; static layout [Confirmed].
- Portrait primary on mobile; grid centered in landscape.

### Art Style [Estimated]
- Flat / minimal design. No gradients except subtle box shadow on selected cell.
- Primary palette (Light theme): Background #F5F5F0 (warm off-white), Grid lines #CCCCCC (thin) and #333333 (box borders), Given digits #1A1A2E (dark navy), Player-entered digits #2563EB (blue), Error digits #DC2626 (red), Selected cell #DBEAFE (light blue tint), Peer cells #EFF6FF, Same-digit highlight #BFDBFE.
- Dark theme: Background #1E1E2E, grid #2D2D44, given digits #E2E8F0, player digits #60A5FA.

### Animation & VFX [Estimated]
- Cell select: instant highlight change (no animation delay needed).
- Digit entry: subtle scale-up pop (transform scale 1.2 → 1.0, 120ms).
- Error entry: horizontal shake (3 cycles, 200ms total) + red flash.
- Win: grid cells do a cascade wave highlight, then confetti particles (CSS).
- Conflict cells: pulsed red background.
- Number pad buttons: press scale-down (0.92) + ripple effect.

### SFX (WebAudio, no files) [Estimated]
| Sound | Trigger | Character |
|-------|---------|-----------|
| Tick | Digit placed correctly | Short sine blip, 800Hz, 80ms |
| Note placed | Pencil digit placed | Softer tick, 600Hz, 60ms |
| Error | Conflict / wrong digit | Low buzz, 150Hz, 200ms |
| Erase | Digit removed | Soft whoosh, 400Hz, 80ms |
| Hint | Hint used | Ascending two-note chime |
| Win | Puzzle complete | 4-note ascending fanfare |

### Music
- Background music: optional; calming ambient loop. In web version, no music (no audio file budget). [Estimated]

---

## 11. Monetization

### Target Clone Model (No Ads, Pure Free)
For the HTML web game deliverable, no ads or IAP are implemented — the game is fully free.

### Reference Model (if monetizing a deployed version) [Estimated]

| Ad Type | Placement | Frequency |
|---------|-----------|-----------|
| Interstitial | After win screen before New Game | Max 1 per 2 puzzles |
| Rewarded Video | "Watch ad for 1 extra hint" | Player-triggered |
| Banner | Below number pad | Always visible (less preferred) |

| IAP Product | Price | Contents |
|-------------|-------|---------|
| Remove Ads | $1.99–$2.99 | No interstitials/banners forever |
| Hint Pack (10) | $0.99 | 10 hint uses |
| Premium Bundle | $3.99 | Remove Ads + Unlimited Hints |

### Loot / Gacha
- None [Confirmed]. Pure skill puzzle, no randomized rewards.

### Consent / ATT / CMP
- For a pure offline HTML game: no tracking, no consent needed.
- If ads added later: GDPR CMP popup on first launch (EU); iOS ATT prompt before any IDFA use.

### Aggressiveness
- Low. Sudoku audience is older, ad-tolerant but not engagement-driven. Rewarded ads only, no forced interstitials in best-practice implementations.

---

## 12. Retention Hooks

### Daily Puzzle [Estimated]
- One new seeded puzzle per day. Push notification: "Your daily puzzle is ready!"

### Streaks [Estimated]
- Consecutive daily solve streak counter. Visual badge at 3, 7, 30 days.

### Personal Best Pressure
- Show personal best time on game screen. "Beat your record!" prompt.

### Offline / Idle Earnings
- **None.** Sudoku is a pure active-play puzzle; no idle mechanics [Confirmed].

### Energy / Lives
- **None** in standard Sudoku. Casual, infinite play [Confirmed].

### Push Notifications [Estimated]
- "Daily puzzle available" (once per day)
- "Can you beat your best time?" (weekly nudge if no play)

### FOMO / Urgency
- Daily puzzle expires at midnight (soft FOMO). Otherwise no urgency mechanics — the tone is calm/relaxing.

---

## 13. Localization & Accessibility

### Supported Languages [Estimated]
- English (default); Japanese, German, French, Spanish, Portuguese, Korean, Chinese (Simplified) for global reach.
- **RTL support:** Not required (digits are universal; UI labels flip in Arabic/Hebrew if added).

### Accessibility [Estimated]
- **Text scaling:** Digit size responsive to grid size; CSS rem-based.
- **Colorblind mode:** Alternative conflict highlight using shape/pattern overlay, not just red/blue.
- **High contrast mode:** Dark theme provides high contrast option.
- **Keyboard navigation:** Full keyboard play (arrows + digits + Delete).
- **Screen reader:** ARIA labels on cells (`aria-label="Row 3 Column 5, empty"`) — [Estimated, aspirational].
- **Font size:** Minimum 18px for digits in 9×9 grid at 375px viewport width.

### Age / Content Rating [Confirmed]
- IARC 3+ / ESRB Everyone. No violence, no adult content.
- COPPA/GDPR-K: Not applicable (no account registration, no data collection in offline HTML version).

### Regional Differences
- None. Sudoku is universal — no regional content differences.

---

## 14. Technical Structure

### Engine & Language [Confirmed for clone]
- Vanilla JavaScript (ES6+), HTML5, CSS3.
- No framework, no build step — single `.html` file deliverable.

### Platform
- Web (desktop + mobile browser); offline-capable via single file double-click.

### Save System [Estimated]
- `localStorage` keys:
  - `sudoku_puzzle` — current puzzle givens (JSON array 81 cells)
  - `sudoku_solution` — full solution (JSON array)
  - `sudoku_progress` — player-filled values + notes (JSON)
  - `sudoku_difficulty` — current difficulty string
  - `sudoku_timer` — elapsed seconds
  - `sudoku_mistakes` — current mistake count
  - `sudoku_best_easy` / `_medium` / `_hard` — best time in seconds
- Auto-save on every cell input; restore on page load.

### Accounts / Auth
- None. Offline, no account required.

### Cross-Device Sync
- None in base version. Export/import puzzle string optional.

### Multiplayer / Netcode
- None [Confirmed]. Single-player only.

### Anti-Cheat / Server Authority
- N/A for single-player offline game [Confirmed].

### Backend / Analytics
- None for offline HTML version.
- If deployed: basic event tracking (puzzle started, completed, difficulty, time) via privacy-respecting analytics (Plausible, Fathom).

### Third-Party SDKs
- None in HTML version.

### App Size
- Single HTML file: estimated 40–80 KB (all inline CSS + JS, no images) [Estimated].

### Performance Notes
- Backtracking solver: runs in <10ms for 9×9 on any modern device [Confirmed].
- Uniqueness check (count solutions, early-exit at 2): <50ms even for hardest puzzles [Estimated].
- No canvas — pure DOM grid for accessibility and simplicity.

---

## 15. Pacing & Difficulty

### Early Game (Easy)
- Many givens → few empty cells → naked singles everywhere → fast, satisfying fills.
- New players learn the rules through instant feedback (conflicts highlighted).
- Typical first solve: 8–15 minutes.

### Mid Game (Medium)
- Requires scanning multiple units simultaneously; hidden singles in boxes.
- Player starts using pencil marks.
- Introduces the "flow state" — continuous deductions without getting stuck.

### Late Game (Hard)
- Long stretches with no obvious move; requires systematic candidate elimination.
- Pencil marks become essential.
- Satisfying "breakthrough" moments when a technique unlocks a chain of placements.

### Difficulty Curve
- Easy → Medium → Hard is the natural progression as player skill grows.
- No artificial difficulty spikes; each puzzle is fair with a guaranteed unique solution.

### Key Milestone / "Aha" Moments [Confirmed]
1. First naked single found: "Oh, only one digit fits here!"
2. First hidden single in a box: more advanced pattern recognition.
3. First time pencil marks enable a placement: tool mastery.
4. First Hard puzzle solved with 0 mistakes: mastery confirmation.

### Churn Points [Estimated]
- Players quit on Hard if they encounter "guessing" (which shouldn't happen with uniquely-solvable puzzles + good technique hints).
- Long sessions without progress → hint system catches this.
- Confusion about pencil marks → tutorial needed.

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---------|-----|------|
| 9×9 grid rendering | ✓ | ✓ |
| Full valid solution generator (backtracking) | ✓ | ✓ |
| Cell removal with uniqueness check | ✓ | ✓ |
| 3 difficulty levels | ✓ | ✓ |
| Locked givens | ✓ | ✓ |
| Click/tap cell selection | ✓ | ✓ |
| On-screen number pad (1–9 + erase) | ✓ | ✓ |
| Keyboard input + arrow navigation | ✓ | ✓ |
| Real-time conflict highlighting | ✓ | ✓ |
| Win detection | ✓ | ✓ |
| Timer | ✓ | ✓ |
| Mistake counter | ✓ | ✓ |
| localStorage save/restore | ✓ | ✓ |
| Notes / pencil-mark mode | ✓ | ✓ |
| Hint (reveal one cell) | ✓ | ✓ |
| Check button (highlight errors) | ✓ | ✓ |
| Same-digit highlighting | ✓ | ✓ |
| WebAudio SFX | ✓ | ✓ |
| Mute toggle | ✓ | ✓ |
| Dark mode | — | ✓ |
| Daily seeded puzzle | — | ✓ |
| Achievements/streaks | — | ✓ |
| Solve (auto-complete) | — | ✓ |
| Colorblind mode | — | ✓ |
| Share result | — | ✓ |

### Phased Roadmap

**Phase 1 — Core Engine (1–2 days)**
1. Generate fully valid 9×9 solution via backtracking with randomized candidate order.
2. Implement uniqueness-check solver (count solutions, abort at 2).
3. Remove cells per difficulty while guaranteeing unique solution.
4. Serialize puzzle to/from JSON.

**Phase 2 — Grid UI (1 day)**
5. Render 9×9 DOM grid with 3×3 box borders.
6. Cell selection + highlight (selected, peer, same-digit).
7. Given cells locked; player cells editable.
8. Conflict detection and highlighting (real-time).

**Phase 3 — Input & Controls (1 day)**
9. On-screen number pad (1–9, Erase, Notes toggle).
10. Keyboard input (digits, Delete/Backspace, arrows).
11. Notes mode: store multiple candidate digits per cell; render as 3×3 mini-grid.
12. Erase: clear main value or notes.

**Phase 4 — Game Logic (0.5 days)**
13. Mistake counter (increment on conflict entry).
14. Win detection (all 81 cells correct).
15. Timer (start on first input; pause on win).
16. Hint (fill one random empty correct cell).
17. Check (flash all incorrect cells).

**Phase 5 — Persistence & Polish (0.5 days)**
18. localStorage save on every change; restore on load.
19. Best-time tracking per difficulty.
20. WebAudio SFX (tick, error, win, hint).
21. Win overlay with time + mistakes.
22. New Game button + difficulty selector.
23. Responsive CSS (mobile-first).
24. Mute toggle.

**Phase 6 — Full Features (1–2 days)**
25. Dark mode toggle.
26. Daily puzzle (seeded RNG by date).
27. Achievements overlay.
28. Auto-solve button.
29. Accessibility (ARIA labels, colorblind mode).

### Recommended Tech Stack

| Layer | Choice | Reason |
|-------|--------|--------|
| Language | Vanilla JS ES6+ | No build step; maximum portability |
| Rendering | HTML/CSS DOM grid | Accessibility; no canvas needed for static puzzle |
| Audio | Web Audio API | No audio files; works offline |
| Storage | localStorage | No server; works offline |
| Styling | CSS custom properties | Easy theming (light/dark) |
| Distribution | Single `.html` file | Double-click playable, offline |

### Required Asset List
- **Art:** Zero external assets — all CSS-drawn shapes and colors.
- **Audio:** Zero files — all WebAudio synthesized.
- **Data:** Puzzle is procedurally generated at runtime; no pre-made puzzle database needed.
- **Fonts:** System sans-serif stack (or one Google Font loaded optionally).

### Hardest Parts / Risks

| Risk | Mitigation |
|------|-----------|
| Uniqueness check is slow for Hard difficulty (many removals) | Cap solver at 2 solutions found (early exit); use randomized symmetric removal order |
| Pencil notes UX on small mobile screens | 3×3 mini-grid within each cell; font 8–9px; only visible when notes exist |
| Conflict highlighting correctness | Pre-compute peer sets for all 81 cells at init; O(1) lookup |
| Save/restore completeness (notes, timer, difficulty) | Serialize full board state as one JSON blob; restore before first paint |
| Mobile number pad not covering grid | Use flexbox column layout; pad fixed at bottom; grid scrolls above |

---

## 17. Open Questions

1. **Difficulty via technique vs givens count:** This clone uses givens count as primary difficulty proxy. A more sophisticated version would analyze required solving techniques; left for post-MVP tuning.
2. **Symmetric removal preference:** Classic Sudoku uses 180° rotational symmetry for aesthetics; the clone can opt in/out — aesthetically nice but not functionally required.
3. **Hard puzzle generation speed:** For very hard puzzles (≤28 givens), uniqueness checking can take 200–500ms; needs testing on low-end Android. Mitigation: pre-generate on idle or use a slightly higher minimum givens floor.
4. **Daily puzzle seed:** A date-based seeded PRNG (e.g., mulberry32 with `YYYYMMDD` as seed) produces consistent daily puzzles without a server; needs validation that seed distribution is uniform across difficulties.
