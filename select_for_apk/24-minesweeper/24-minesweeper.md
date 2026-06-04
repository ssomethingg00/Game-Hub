# Minesweeper — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Minesweeper is a single-player logic puzzle game played on a rectangular grid of hidden cells. The player's goal is to reveal every non-mine cell using numerical clues about adjacent mine counts, while marking suspected mines with flags. It is one of the most played puzzle games ever made, pre-installed on Windows from 3.1 through 7, later available via the Microsoft Store. The game has a large competitive speedrunning community and spawned hundreds of web clones.

**Quick facts:**
- **Developer/Publisher:** Originally Robert Donner & Curt Johnson (Microsoft), 1990; countless independent clones exist
- **Platforms:** Web, Windows, macOS, iOS, Android, Linux (via native or browser clones)
- **Genre:** Logic puzzle / Deduction puzzle
- **Session length:** 30 seconds (Beginner) – ~5 minutes (Expert)
- **Play style:** Active, single-player, turn-based
- **Age/content rating:** ESRB E (Everyone) — no violence, gambling, or mature content [Confirmed]
- **Monetization model (clone):** None required; optional ad-supported or premium no-ads

---

## 2. Core Loops

- **30-second loop:** Left-click an unrevealed cell → see a number (1–8) or empty reveal with flood-fill → right-click suspected mines to place/remove flags → deduce next safe cell from numbers → repeat until win or mine hit.
- **Session loop:** Choose difficulty → first click guaranteed safe (sometimes opens large empty region) → methodically reveal cells using deduction + probabilistic guessing → either win (all safe cells revealed) and see timer → or hit a mine, board reveals all mines, game over → restart or change difficulty. One session = one full game; typically 30 s to 5 min.
- **Meta loop:** Beat personal best time per difficulty stored in localStorage → compete against memory/friends → try harder difficulty → chase 3BV (minimum clicks to solve) efficiency on Expert. No long-term progression system; replayability comes from random board generation and time chasing.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics

| Mechanic | Description |
|---|---|
| Grid of hidden cells | Rectangular grid; all cells start covered/unrevealed |
| Mine placement | Mines placed randomly after first click (first-click-safe guarantee) |
| Reveal | Click a cell to uncover it; reveals a number 1–8 (adjacent mine count) or blank (0 mines adjacent, triggers flood-fill) |
| Flood-fill / cascade | Revealing a 0-cell recursively reveals all 8 neighbors; continues until all bordering cells are numbered or flagged |
| Flag | Right-click (desktop) or flag-mode toggle (mobile) marks a cell with a flag; flagged cells cannot be accidentally revealed; flag count tracked in HUD |
| Chord | Clicking a revealed number when its adjacent flag count equals the number instantly reveals all non-flagged neighbors (risky but fast) [Confirmed — standard in Windows versions] |
| Mine counter | HUD shows remaining mines = total mines − flags placed (can go negative) |
| Timer | Starts on first click; stops on win or lose; stored as best time per difficulty |
| Win condition | All non-mine cells revealed (flagging is NOT required) |
| Lose condition | Revealing a mine; all mines shown, triggering red-mine highlight on the detonated cell |
| First-click safety | Mine placement is deferred until after the first click; the clicked cell (and ideally its 8 neighbors) is guaranteed mine-free, often opening a zero region |

### Core Verbs

- **Reveal** (left-click / tap)
- **Flag / unflag** (right-click / long-press / flag-mode tap)
- **Chord** (middle-click or left-click on satisfied number)
- **Reset** (click face button / F2)
- **Change difficulty** (dropdown/buttons)

### Game Modes

| Mode | Description |
|---|---|
| Beginner | 9×9 grid, 10 mines [Confirmed] |
| Intermediate | 16×16 grid, 40 mines [Confirmed] |
| Expert | 30×16 grid, 99 mines [Confirmed] |
| Custom (optional) | User-set rows/cols/mines |

### Input Scheme

- **Desktop:** Left-click = reveal; right-click = flag; middle-click or left-click number = chord; no keyboard required (F2 = reset is optional classic shortcut)
- **Mobile:** Tap = reveal; long-press OR flag-mode toggle button = flag; no chord required on mobile
- **Orientation:** Landscape preferred for Expert; portrait works for Beginner/Intermediate if grid scrolls

### Win / Lose / Fail States

| State | Trigger | Result |
|---|---|---|
| Win | All non-mine cells revealed | Timer stops; best time updated if new record; win overlay shown; face changes to sunglasses |
| Lose | Player reveals a mine | All mines revealed; detonated mine highlighted red; crossed-out face; lose overlay |
| Mid-game | Ongoing | Face = nervous (mouse held down, optional) |

### Feedback Systems

- **Visual:** Cells depress/reveal with CSS transition; number colors (1=blue, 2=green, 3=red, 4=darkblue, 5=maroon, 6=teal, 7=black, 8=gray) [Confirmed — classic palette]
- **Audio (WebAudio SFX):** Click sound on reveal; flag place/remove sound; boom on mine; fanfare on win
- **Face button:** Changes state — default smile, scared on mousedown, sunglasses on win, dead on lose

---

## 4. Progression

Minesweeper has no long-term character/item progression. The only progression is:

- **Best time per difficulty** stored in localStorage (Beginner / Intermediate / Expert)
- **Implicit skill progression:** Player improves pattern recognition over time (1-2-1 patterns, 50/50 deduction, corner logic)
- **Difficulty ladder:** Beginner → Intermediate → Expert as natural skill progression
- **No unlock trees, prestige, XP, or gating beyond choosing a harder preset** [Confirmed]

---

## 5. Economy & RNG *(tables)*

### Currencies

No in-game currency system. The only scarce resource is time (the timer).

| "Currency" | Type | Earned From | Spent On |
|---|---|---|---|
| Seconds | Time | — | Trying to minimize; best time stored |
| Flags | Limited resource | Starts = mine count | Marking suspected mines |

### Mine Placement RNG

| Parameter | Value | Tag |
|---|---|---|
| Mine placement algorithm | Fisher-Yates shuffle on all cells except first-click cell (+ optional 8 neighbors) | [Confirmed] |
| Mines per difficulty | Beginner: 10, Intermediate: 40, Expert: 99 | [Confirmed] |
| Mine density | Beginner: ~12.3%, Intermediate: ~15.6%, Expert: ~20.6% | [Confirmed] |
| Guaranteed no-guess solvability | NOT guaranteed in original; some boards require a 50/50 guess | [Confirmed] |
| First-click zone clear | Clicked cell + all 8 neighbors excluded from mine placement | [Estimated — some clones use just the 1 cell; 9-cell clear is friendlier] |

### Cost Scaling
No economy to scale. N/A.

### Drop Rates / Gacha
None. No loot, no gacha, no RNG beyond mine placement. [Confirmed]

---

## 6. Content Inventory *(counts + lists)*

| Category | Count | Details |
|---|---|---|
| Difficulty presets | 3 | Beginner, Intermediate, Expert |
| Grid sizes | 3 (+ custom optional) | 9×9, 16×16, 30×16 |
| Mine counts | 3 | 10, 40, 99 |
| Number tiles | 8 types | 1–8, each with distinct color |
| Special tiles | 4 | Blank (0), Unrevealed, Flagged, Mine |
| Face states | 4 | Smile, Scared, Sunglasses, Dead |
| Screens | 3 | Game screen, Win overlay, Lose overlay |
| Best-time records | 3 | One per difficulty in localStorage |
| SFX | 4–5 | Click, Flag, Unflag, Boom, Win fanfare |

No characters, story levels, collectibles, or enemies beyond the mine tiles.

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract grid — no real-world metaphor required; original used a minefield theme (bombs on a field). Clone can use any clean abstract theme (cells, tiles, nodes).
- **Premise:** None. Pure puzzle with zero narrative. The "story" is implied: sweep the field without triggering a mine.
- **Tone:** Calm, focused, slightly tense when guessing. Clean and utilitarian — the UI IS the game.
- **Visual theme options for clone:** Classic Windows 95 gray + beveled tiles (retro), flat modern minimalist, dark mode cyberpunk, pastel casual.
- **Dialogue / writing style:** Minimal — difficulty labels, "Best: MM:SS" display, win/lose overlay text ("You Win!" / "Game Over").
- **IP:** Original is Microsoft IP. A clone must use different branding, different tile art, no Microsoft logos. Mechanics are not copyrightable. [Confirmed — genre mechanics are fair use]

---

## 8. Meta & Social Systems

### Present in Original / Common Clones

| System | Status |
|---|---|
| Best time per difficulty | Core feature [Confirmed] |
| Global leaderboards | Not in classic; some web clones add it [Estimated] |
| Custom difficulty | Present in classic Windows version [Confirmed] |
| Daily challenge / seed mode | Not in original; common in modern clones [Estimated] |
| Share result | Not in original; can add social share of win time [Estimated] |

### Live-Ops Cadence
None required. Minesweeper is a static puzzle — no live-ops, events, or seasons needed. A clone can ship and maintain indefinitely with zero new content. [Confirmed]

### Multiplayer / Social
None in original. Some modern variants add race-style co-op or versus but this is out of scope for a clone MVP. [Confirmed]

### Missions / Achievements (Optional Enhancements)
- Win Beginner under 10 s
- Win Expert without guessing
- Complete all 3 difficulties
These are enhancement features, not core. [Estimated]

---

## 9. UI/UX & Screen Map

### Screen List

| Screen | Purpose |
|---|---|
| Main game screen | Primary play surface — contains HUD + grid |
| Win overlay | "You Win!" + time + best time; play again button |
| Lose overlay | "Game Over"; reveals mines; play again button |
| Settings panel (optional) | Mute toggle, best time reset, about/credits |

### Gameplay HUD Elements

| Element | Position | Function |
|---|---|---|
| Mine counter (LCD) | Top-left | Mines remaining = total − flags placed; 3-digit display |
| Face/reset button | Top-center | Click to reset; shows game state via face emoji/icon |
| Timer (LCD) | Top-right | Seconds elapsed since first click; 3-digit display |
| Difficulty selector | Above HUD or below | Tabs or dropdown: Beginner / Intermediate / Expert |
| Best time display | Below HUD or settings | "Best: 00:00" per active difficulty |
| Flag-mode toggle | Below grid (mobile) | Toggle between reveal-mode and flag-mode on touch devices |
| Mute button | Corner | Toggle all SFX on/off |

### Navigation Flow

```
Load page
  └─> Game Screen (Beginner default)
        ├─> Select difficulty → rebuild grid, reset timer/counter
        ├─> Click cell → start timer → reveal / flag / chord
        │     ├─> Hit mine → Lose overlay → [Play Again] → new game
        │     └─> Clear all safe cells → Win overlay → [Play Again] → new game
        └─> Face button → reset current difficulty
```

### Onboarding / Tutorial (Step-by-Step)

1. Page loads with Beginner grid and a brief tooltip: "Left-click to reveal, right-click to flag"
2. First click guaranteed safe — a region opens
3. Numbers appear — optional mini-hint: "Numbers show adjacent mine count"
4. On first flag placed — optional mini-hint: "Flags mark suspected mines"
5. On first win — win overlay displayed with time; no forced tutorial beyond tooltips [Estimated — matches common web clone approach]

No mandatory tutorial; the game teaches itself through play.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **Dimension:** 2D flat grid
- **Camera:** None — static top-down orthographic tile grid
- **Orientation:** Landscape preferred (especially Expert 30×16); responsive to portrait with horizontal scroll or cell scaling

### Art Style
- **Classic option:** Windows 95 beveled gray squares — raised unrevealed, sunken revealed; LCD-style 7-segment mine counter + timer; classic face button
- **Modern option:** Flat design with subtle shadow; rounded corners; clean sans-serif numbers
- **Recommended palette:** Mid-gray background (#C0C0C0 classic, or #1a1a2e dark), white/light revealed cells, colored numbers per classic spec:
  - 1 = #0000FF (blue), 2 = #008000 (green), 3 = #FF0000 (red), 4 = #00008B (dark blue)
  - 5 = #8B0000 (maroon), 6 = #008B8B (teal), 7 = #000000 (black), 8 = #808080 (gray)

### Animation & VFX
- Cell reveal: subtle scale-in or fade
- Mine boom: red flash on detonated cell + screen shake (subtle)
- Win: brief particle burst or tile shimmer
- Flag place: small pop animation
- Face button: updates frame on each game state change

### SFX (WebAudio API — no external files)
| Sound | Trigger | Character |
|---|---|---|
| Click | Cell reveal | Short crisp tick (sine tone ~800 Hz, 50ms) |
| Flag | Flag placed | Soft thud (noise burst, 80ms) |
| Unflag | Flag removed | Higher-pitch click |
| Boom | Mine hit | Low rumble + distortion (100ms noise + pitch drop) |
| Win fanfare | All safe cells cleared | Ascending 3-note chime |

### Music
None in original. Background ambient music optional but not recommended — focus is deduction, not atmosphere. [Estimated — consistent with classic behavior]

---

## 11. Monetization

This blueprint targets a free-to-play web clone with no monetization required for the core experience. A deployed product may optionally add:

| Type | Placement | Notes |
|---|---|---|
| None (MVP) | — | Fully playable free game |
| Banner ad (optional) | Below game grid | Non-intrusive; only on page load |
| Rewarded video (optional) | "Continue" after losing | Skippable; player opted-in |
| "No ads" IAP (optional) | Settings screen | One-time purchase via Web Payments API |

**Consent/ATT/CMP:** If ads are added, a GDPR/CMP consent banner is required for EU users; iOS ATT prompt required if running as PWA/app. For a no-ad web clone, no consent flows are needed. [Confirmed — no-ad clone = no consent infrastructure needed]

**Aggressiveness:** Zero — this is a classic puzzle game clone. Any ads should be entirely passive.

---

## 12. Retention Hooks

Minesweeper relies on intrinsic motivation, not engineered retention:

| Hook | Implementation |
|---|---|
| Best time records | localStorage per difficulty — "beat your best" motivation |
| Difficulty progression | Natural ladder from Beginner → Expert |
| Near-win tension | Probabilistic endgame guessing creates "one more game" pull |
| Clean restart | Face button → instant new game; zero friction |
| No lives/energy system | Play forever at no cost — zero paywalls [Confirmed] |
| No daily rewards | Not applicable to classic Minesweeper [Confirmed] |
| No idle/offline earnings | N/A — active puzzle game [Confirmed] |
| Push notifications | Not applicable for web clone [Confirmed] |

---

## 13. Localization & Accessibility

### Localization
| Item | Status |
|---|---|
| Supported languages | English only for MVP; all text is minimal (number labels, difficulty names) [Estimated] |
| RTL support | Not needed — UI is symmetric grid; no long text runs [Estimated] |
| Regional pricing | N/A (free clone) |

### Accessibility
| Feature | Approach |
|---|---|
| Text scaling | Cell size responsive; HUD text in rem units |
| Colorblind mode | Numbers use BOTH color AND bold weight; optionally add shape indicators on cells [Estimated] |
| Keyboard navigation | Tab through cells; Enter = reveal; Space = flag; optional enhancement [Estimated] |
| High contrast | Dark mode toggle (light/dark palette swap) [Estimated] |
| Font | Large sans-serif (min 14px) for numbers; avoid small text on mobile |
| Difficulty assist | Beginner serves as easy mode; no lives/punish beyond losing the game |
| Screen reader | aria-label on cells ("unrevealed", "flagged", "mine count 3") — optional [Estimated] |

### Age / Content Rating
- **ESRB:** E (Everyone) — abstract mines, no blood, no violence imagery [Confirmed]
- **COPPA/GDPR-K compliance:** No personal data collected, no accounts, no ads in MVP → fully compliant with children's privacy laws by default [Confirmed]

---

## 14. Technical Structure

### Engine & Language
| Item | Choice |
|---|---|
| Engine | Vanilla HTML5 + CSS3 + ES6 JavaScript — no framework needed |
| Rendering | DOM (div grid) for simplicity; Canvas is viable but adds complexity for no benefit in a tile puzzle |
| Build step | None — single .html file, inline CSS + JS |
| Package manager | None |

### Platform Support
- **Primary:** Web (all modern browsers: Chrome, Firefox, Safari, Edge)
- **Mobile:** Responsive CSS + touch events; no native app needed
- **Offline:** Fully offline — no network requests whatsoever

### Save System
| Item | Implementation |
|---|---|
| Best times | localStorage keyed by difficulty ("ms_best_beginner", etc.) |
| Last difficulty | localStorage ("ms_last_difficulty") |
| No cloud sync | N/A for single-file web game |
| No accounts/auth | N/A |

### Multiplayer / Anti-Cheat
Not applicable. Single-player, offline, no server. [Confirmed]

### Backend / Analytics
None for MVP. Optional: simple privacy-respecting ping to count plays (no PII). [Estimated]

### Performance Notes
- Expert grid = 480 cells (30×16); DOM grid at this scale is fast — no virtualization needed
- Mine placement algorithm: O(n) Fisher-Yates on cell array
- Flood-fill: BFS with visited set to prevent infinite loops — critical correctness requirement
- Chord: iterate 8 neighbors only; negligible cost
- Target: 60fps UI on any phone from 2018+; no animations heavier than CSS transitions

---

## 15. Pacing & Difficulty

### Early Game (Beginner — 9×9, 10 mines)
- Low mine density (~12%) makes large zero-reveals common
- Most cells deduce trivially; minimal guessing
- Typical win time: 10–60 s for a competent player [Confirmed]
- "Aha" moment: first flood-fill cascade opening a big region
- World record: ~0.5 s (sub-1 second humanly possible with luck) [Confirmed]

### Mid Game (Intermediate — 16×16, 40 mines)
- ~15.6% density; more constrained deduction; some forced guesses
- Corners and edges create classic 1-2-1, 1-1-2, tank-solver patterns
- Typical win time: 30 s – 3 min [Confirmed]
- Churn point: players who hate guessing stall here

### Late Game (Expert — 30×16, 99 mines)
- ~20.6% density; multiple forced 50/50 guesses per game
- Requires full pattern library + probabilistic reasoning
- Typical win time: 40 s – 8 min (world record ~26 s) [Confirmed]
- Churn: players who can't accept probabilistic death quit here; speedrunners stay forever

### Difficulty Curve
```
Beginner:     [Easy open] ──→ [Trivial deduction] ──→ [Win / minor guess]
Intermediate: [Medium open] → [Pattern recognition] → [1-2 forced guesses] → [Win/Lose]
Expert:       [Small open] ──→ [Complex patterns] ──→ [Multiple 50/50s] ──→ [Win/Lose]
```

### Key Milestone Moments
1. First flood-fill cascade — "wow, big reveal"
2. First successful deduction (placing correct flag) — "I figured it out"
3. First clean win — "I did it"
4. First Expert completion — peak achievement
5. Beating personal best time — repeat motivation

---

## 16. Clone Build Plan

### MVP Feature Set

| Feature | MVP | Full |
|---|---|---|
| 3 difficulty presets | Yes | Yes |
| Reveal + flood-fill | Yes | Yes |
| Flagging | Yes | Yes |
| Mine counter + timer | Yes | Yes |
| First-click-safe | Yes | Yes |
| Win / lose detection + overlay | Yes | Yes |
| Best time localStorage | Yes | Yes |
| Mobile flag-mode toggle | Yes | Yes |
| Reset button (face) | Yes | Yes |
| Chord mechanic | No | Yes |
| WebAudio SFX | No | Yes |
| Mute toggle | No | Yes |
| Dark/light mode | No | Yes |
| Keyboard navigation | No | Yes |
| Custom difficulty | No | Yes |
| Global leaderboard | No | Yes |
| Daily seed challenge | No | Yes |
| Colorblind mode | No | Yes |

### Phased Build Roadmap

**Phase 1 — Core Engine (1–2 days)**
1. HTML structure: HUD bar + grid container + overlays
2. Grid generation function (rows, cols, mines params)
3. First-click-safe mine placement (Fisher-Yates, exclude clicked cell + 8 neighbors)
4. Adjacency count computation for all cells
5. Reveal function with BFS flood-fill (zero-cell cascade)
6. Win/lose detection

**Phase 2 — UI & Controls (1 day)**
1. CSS styling: raised/sunken cells, number colors, HUD LCD style
2. Left-click reveal, right-click flag, context-menu suppression
3. Mine counter (total − flags), timer (starts on first click, stops on win/lose)
4. Face button states: smile / scared / sunglasses / dead
5. Difficulty selector rebuilds grid
6. Win/lose overlays with best-time display
7. localStorage save/load for best times

**Phase 3 — Mobile & Polish (0.5 day)**
1. Responsive grid scaling (viewport width / cell count)
2. Touch events: tap = reveal, long-press or flag-mode toggle = flag
3. Prevent scroll during grid touch
4. Test on 375px (iPhone SE) viewport
5. Flag-mode toggle button below grid on mobile

**Phase 4 — Juice & Audio (0.5 day)**
1. WebAudio SFX: click, flag, boom, win fanfare
2. Mute toggle
3. Cell reveal animations (CSS transition)
4. Mine boom: red flash + subtle shake
5. Win: brief shimmer/particle on grid
6. Chord mechanic (left-click satisfied number)

**Phase 5 — QA & Polish (0.5 day)**
1. Test all three difficulties: adjacency counts correct, no mine on cell 0
2. Flood-fill: BFS terminates, no infinite loop
3. Win detection: fires exactly when last safe cell revealed
4. Lose: all mines shown, red mine highlighted
5. Timer: starts on first click, stops correctly
6. Flag count: mine counter goes negative if over-flagged (matches classic)
7. localStorage: best time saved, loaded, displayed
8. Right-click + mobile flag toggle both work
9. No console errors in Chrome/Firefox/Safari
10. File ends with </html>

### Recommended Tech Stack (Clone)

| Layer | Choice | Reason |
|---|---|---|
| Language | Vanilla ES6 JS | Zero dependencies, runs offline |
| Rendering | HTML DOM (divs) | Simple, accessible, no canvas needed |
| Styling | Inline CSS in `<style>` | Single-file requirement |
| Audio | WebAudio API | No external files; generates tones procedurally |
| Storage | localStorage | No backend required |
| Build | None | Double-click to run |

### Required Asset List

All assets generated programmatically — NO external files needed:

| Asset | Method |
|---|---|
| Mine icon | Unicode ● or CSS-drawn circle + spike SVG |
| Flag icon | Unicode 🚩 or CSS triangle + pole |
| Face button | Unicode emoji: 🙂 😰 😎 💀 or CSS-drawn |
| Number tiles | CSS text with color per number |
| SFX | WebAudio API oscillator + noise synthesis |
| Background | CSS gradients |

### Hardest Parts / Risks

| Risk | Mitigation |
|---|---|
| BFS flood-fill infinite loop | Use a `visited` Set; enqueue only unvisited cells; test extensively on zero-mine boards |
| First-click-safe on small boards | If mine count is high relative to grid, exclusion zone may conflict; clamp exclusion to available cells |
| Win detection false negative | Trigger on every reveal; count revealed non-mine cells vs total non-mine cells; never rely on flag state |
| Mobile long-press vs scroll conflict | Use `touchstart`/`touchend` timing (>400ms = flag); call `preventDefault()` carefully to not block scroll outside grid |
| Expert grid on narrow mobile | 30 columns × min 28px = 840px; allow horizontal scroll on grid container without scrolling the whole page |
| Context menu on right-click | `addEventListener('contextmenu', e => e.preventDefault())` on grid container |
| Chord accidental mine reveal | Chord only fires when flagCount === cellNumber; verify before revealing; non-flagged neighbors may contain mines |

---

## 17. Open Questions

1. **No-guess solvability:** Some competitive players demand a solvable-without-guessing board. Implementing a constraint-satisfaction solver to verify and regenerate boards adds significant complexity. For MVP, accept the classic behavior (guessing possible). [Needs playtesting to determine player tolerance]

2. **Chord on mobile:** Touch-based chord (tap a number when neighbors are flagged) UX is ambiguous — could be confused with normal tap. May need a dedicated long-press-on-number interaction or an explicit "chord mode". [Estimated behavior; needs UX testing]

3. **Timer precision:** Classic Minesweeper counts whole seconds. Modern clones use milliseconds for competitive play. Decide: integer seconds (classic feel) vs. ms precision (competitive). [Design decision; not a research gap]

4. **Expert grid scrolling UX on mobile:** Horizontal scroll within a fixed-height game frame is technically solvable but the UX can feel awkward. Alternative: scale cells down to ~20px on mobile for Expert. [Needs device testing]

---

*Blueprint complete. All 17 sections filled. Economy/RNG: confirmed no currency system, mine placement RNG documented. Win/lose states defined. Screen map complete. Monetization: MVP has none; optional paths documented. Retention: intrinsic-only, no artificial hooks. COPPA/GDPR-K: fully compliant by default (no data collection). Build plan phased with hardest risks flagged. Anti-cheat: N/A (single-player offline). Each non-obvious claim tagged [Confirmed] or [Estimated].*
