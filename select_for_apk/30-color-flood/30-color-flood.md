# Color Flood (Flood-it) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Color Flood (popularly known as "Flood-it") is a single-player puzzle game where the player controls a flood region that starts at the top-left corner of a colored grid. Each turn the player picks a color from a palette; the current flood region changes to that color and immediately absorbs any adjacent cells that match the new color, expanding the region. The goal is to flood the entire grid with one color before running out of moves. It is a pure strategy puzzle — no time pressure, no luck beyond the random board — delivering calm, satisfying "one more game" appeal.

**Quick facts:**
- Developer: Multiple — notable versions by Lab Pixies (original Android), rmaalouf, Land Of Games, and many indie clones
- Platforms: Android, iOS, Web (browser), Windows Store [Confirmed]
- Release: Original Lab Pixies ~2010; modern clones ongoing [Confirmed]
- Rating: 4+ / Everyone (ESRB E) — suitable for all ages [Confirmed]
- Monetization model (original apps): ad-supported free with optional IAP to remove ads; clone here is ad-free single-page web app
- Core appeal: easy to learn, hard to optimize; replayable via random boards; "beat your best" scoring hook

---

## 2. Core Loops

- **30-second loop:** Scan the board → identify which color pick will absorb the most cells → tap a color button → watch the flood region expand → repeat.
- **Session loop:** Play a randomly generated board (1–5 min), finish within the move limit (win) or exhaust moves (lose), see move count vs. personal best, tap New Game.
- **Meta loop:** Chase a lower personal best for each grid-size / color-count configuration saved in localStorage; unlock a sense of mastery as optimal-path intuition develops over many sessions.

---

## 3. Mechanics, Controls & Game States

### Core mechanic — flood fill
- The **flood region** is the contiguous connected group of cells that shares the same color as the top-left cell (cell [0,0]) at the start of the game, expanded with each move.
- On each move: player picks a color C from the palette.
  - If C equals the current flood color → **no-op** (move is NOT consumed). [Estimated — common convention; some versions do count it, but the no-op variant is more user-friendly]
  - Otherwise: the entire flood region changes to color C; then BFS/DFS expands the region to absorb all orthogonally adjacent cells on the board that are color C. This expansion is recursive — newly absorbed cells can absorb further adjacent cells of the same color in the same step.
- **Win condition:** The flood region covers every cell on the board (all cells are one color). Triggered after each move that might complete the board.
- **Lose condition:** The move counter reaches the move limit and the board is not yet fully flooded.
- **Move counting:** Each valid (non-no-op) color pick increments the move counter by 1.
- **Failure handling:** "You lose" overlay; player can restart (New Game) immediately. No lives system, no penalty currency.

### Game modes
| Mode | Description |
|------|-------------|
| Standard (default) | Random board, fixed move limit, chase best score |
| Size options | 8×8, 10×10, 12×12, 14×14 (default), 16×16 — each has its own best score |
| Color options | 4, 5, 6 (default), 7, 8 colors — fewer = easier, more = harder |

### Controls
- **Mouse / tap:** Click a color swatch in the palette bar to pick that color.
- **Keyboard:** Number keys 1–8 map to palette colors 1–8 (key 1 = leftmost color).
- **Orientation:** Portrait-first (mobile); also works landscape (desktop).

### AI / Difficulty
- No AI opponent. Difficulty comes from grid size and color count.
- Internal solver (greedy region-count heuristic) sets move-limit baseline during board generation. [Estimated]

### Feedback systems
- **Flood sweep animation:** The flood region visibly expands outward cell-by-cell with a brief ripple/wave animation (~150–200 ms total).
- **Color button highlight:** The currently active flood color is visually marked (ring, glow, or scale-up).
- **Win:** Confetti particle burst + triumphant chime; overlay shows move count and personal best.
- **Lose:** Subtle screen shake + descending tone; overlay prompts retry.
- **Progress indicator:** Percentage of board flooded shown live in HUD.

---

## 4. Progression

- **No persistent XP or unlock tree.** The game is stateless except for personal-best scores per config.
- **Implicit skill progression:** As the player improves their mental model of region merging, they naturally use fewer moves, lowering their personal best.
- **Config unlocks:** All grid sizes and color counts are available from the start (no gating). [Estimated — most web clones do this]
- **Difficulty gating:** Move limit tightens proportionally with grid size and color count (see Section 5), so larger grids with more colors form a natural difficulty ladder.

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned from | Spent on |
|----------|------|-------------|----------|
| None | — | — | — |

This is a pure puzzle game with no in-game economy. The only tracked value is the **personal best** (minimum moves to solve) per configuration, stored in localStorage.

### Move-limit formula

[Confirmed from multiple sources] The standard 14×14 / 6-color configuration uses a limit of **25 moves**. Expert players solve it in 18–20 moves.

General formula for the move limit [Estimated]:

```
moveLimit = floor(baseMoves * colorFactor * sizeFactor + buffer)
```

Practical lookup table (Estimated, tuned for ~70–80% win rate on first attempt with reasonable play):

| Grid | Colors | Estimated min solve | Move limit |
|------|--------|---------------------|------------|
| 8×8  | 4      | 8–10                | 14         |
| 8×8  | 6      | 10–13               | 18         |
| 10×10| 5      | 12–15               | 20         |
| 10×10| 6      | 14–17               | 22         |
| 12×12| 6      | 17–21               | 27         |
| 14×14| 6      | 18–22               | 25 [Confirmed] |
| 16×16| 6      | 22–27               | 32         |
| 16×16| 8      | 26–32               | 38         |

Buffer above estimated minimum: approximately +4 to +7 moves [Estimated].

### RNG
- Board generation: each cell assigned a uniformly random color from the palette (Math.random). No seeding by default (each game is fresh).
- No loot tables, no gacha, no drop rates. Randomness is purely in board layout.

---

## 6. Content Inventory *(counts + lists)*

| Category | Count | Detail |
|----------|-------|--------|
| Grid sizes | 5 | 8×8, 10×10, 12×12, 14×14, 16×16 |
| Color counts | 5 | 4, 5, 6, 7, 8 |
| Color palettes | 2 | Standard vivid; Colorblind-friendly (Okabe-Ito / IBM palette) |
| Game modes | 1 | Single-player puzzle (all sizes/colors are one mode) |
| Levels / campaigns | 0 | Random boards only; no predefined level set |
| Characters | 0 | Abstract puzzle; no characters |
| Items / power-ups | 0 | None in base game |
| Enemies | 0 | None |
| Achievements | 3 (optional) | Win on first try; beat board in ≤ solver moves; new personal best |

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract / geometric. A flat grid of colored squares — no story world.
- **Premise:** None. There is no narrative. The game is pure mechanic.
- **Tone:** Calm, satisfying, slightly competitive (beating your own best). No gritty or tense elements.
- **Visual theme:** Clean flat design; bright saturated cell colors against a neutral dark or white background.
- **Copy/writing style:** Minimal — only a handful of UI labels ("Moves", "Best", "New Game", "Win!", "Game Over", "Colors", "Size").
- **Licensed IP:** None. Original IP for the clone should use a distinct name (e.g., "Chromatic Flood", "TileWave", "HueFlood").

---

## 8. Meta & Social Systems

- **No multiplayer, leaderboards, guilds, or social features** in the base game. [Confirmed for most single-player web clones]
- **Personal best:** Per-config high score stored in localStorage — the only meta hook.
- **Daily challenge (optional enhancement):** Could seed the board by date so all players play the same puzzle. Not in MVP.
- **Achievements (optional):** In-session badges (Win!, New Best!, Perfect) shown in overlay.
- **Live-ops:** None required. Static puzzle with infinite replay via randomness.
- **Sharing (optional):** "Share result" button that copies a text summary (emoji grid-like result code) to clipboard — low-effort social hook.

---

## 9. UI / UX & Screen Map

### Screen list
| Screen | Purpose |
|--------|---------|
| Game screen (only screen) | The entire game lives on one screen: options strip, board, palette, HUD |
| Win overlay | Animated modal over the board: move count, personal best, share button, New Game |
| Lose overlay | Modal over board: "Out of moves!", move count, New Game |
| Settings panel (slide-in) | Mute toggle, colorblind mode toggle, about/credits |

### Settings / options menu contents
- Sound on/off toggle
- Colorblind palette toggle
- Grid size selector (8–16)
- Color count selector (4–8)
- Credits / version

### HUD elements (during gameplay)
- Moves used / move limit (e.g., "12 / 25")
- Percent flooded (e.g., "67%")
- Personal best for this config (e.g., "Best: 19")
- Mute icon (top corner)
- New Game button
- Settings gear icon

### Navigation flow
```
Game Screen ─── tap Settings gear ──▶ Settings Panel (slide-in)
            ◀── tap X/close ──────────
            ─── win/lose ──────────▶ Overlay modal
            ◀── tap New Game ──────────
```

### Onboarding / tutorial (first-time user flow)
1. Game loads → board is already displayed with 14×14 default grid.
2. A small tooltip arrow points to the palette and says "Pick a color to flood from the top-left."
3. Player taps any color → flood fills → tooltip disappears permanently (flag in localStorage).
4. No further tutorial — mechanic is self-explanatory within 2–3 moves.

---

## 10. Art, Audio, Camera & Feel

### Dimension & camera
- **2D, flat.** No camera — pure DOM grid or Canvas with fixed top-down view.
- **Orientation:** Portrait primary (mobile); responsive to landscape and desktop.

### Art style
- Flat design; no gradients on cells. Each color is a solid fill with a very thin (1px) dark separator or gap between cells.
- Cell size: scales dynamically so the entire board fits within the viewport with the palette bar below.
- Palette bar: row of large rounded squares (min 48×48 px touch target) below the board.
- Current-flood-color indicator: the active color button has a white outline ring + slight scale(1.15).
- Font: system sans-serif (e.g., -apple-system, Segoe UI). Large move counter, smaller labels.

### Color palettes
**Standard (vivid):**
| # | Name | Hex |
|---|------|-----|
| 1 | Red | #E8365D |
| 2 | Orange | #F4A020 |
| 3 | Yellow | #F5E642 |
| 4 | Green | #3DBE6C |
| 5 | Blue | #3B82F6 |
| 6 | Purple | #9B5CF6 |
| 7 | Pink | #EC4899 |
| 8 | Teal | #14B8A6 |

**Colorblind-friendly (Okabe-Ito):**
| # | Name | Hex |
|---|------|-----|
| 1 | Orange | #E69F00 |
| 2 | Sky Blue | #56B4E9 |
| 3 | Blueish Green | #009E73 |
| 4 | Yellow | #F0E442 |
| 5 | Blue | #0072B2 |
| 6 | Vermilion | #D55E00 |
| 7 | Reddish Purple | #CC79A7 |
| 8 | Black | #000000 |

### Animation & VFX
- **Flood sweep:** On each move, newly absorbed cells animate with a quick scale-up pop (transform: scale 0.8→1.0) or a CSS class flash over ~150 ms. [Estimated]
- **Win:** CSS confetti (colored divs or canvas particles falling) + a golden glow on the board border.
- **Lose:** Board briefly shakes (CSS keyframe translate X ±4px, 3 cycles, 300 ms total).
- **Button press:** Color button depresses on click (scale 0.9, 80 ms).

### Audio (WebAudio generated, no files)
- **Flood move:** Short soft "whoosh" or bubble-pop tone — sine wave, 200 Hz → 400 Hz, 120 ms, low volume.
- **Win:** Ascending 4-note chime (C4-E4-G4-C5), each 100 ms.
- **Lose:** Descending 2-note tone (G4 → D4), 200 ms.
- **New game:** Quick soft tick, 50 ms.
- **Mute toggle:** All AudioContext calls wrapped in a mute flag.

---

## 11. Monetization

- **This clone is a free offline web game — no monetization.** [Estimated — spec says ad-free HTML]
- **Original apps:** ad-supported (interstitial between games, rewarded optional); IAP ~$1.99 to remove ads. No gacha or loot boxes in any known version. [Confirmed from store listings]
- **ATT/GDPR:** Not applicable to this offline HTML clone. If deploying to a web server with ads, a standard CMP banner would be needed. Age rating: suitable for all ages (no COPPA concern for pure puzzle with no data collection).

---

## 12. Retention Hooks

- **Personal best per config:** Displayed prominently; the only number the player wants to improve. Psychological "beat yourself" loop.
- **No idle/offline earnings:** Game is discrete-puzzle; no passive mechanics. [Confirmed — genre has none]
- **No lives or energy system:** Player can retry instantly; no friction.
- **No push notifications:** Offline HTML game.
- **FOMO / urgency:** None intentional. Move limit creates in-game urgency per puzzle only.
- **"One more game" hook:** Random board + fast reset makes replaying trivially easy.

---

## 13. Localization & Accessibility

### Localization
- **English only** in this clone (minimal text makes translation trivial if needed). [Estimated]
- No RTL support needed for minimal UI.
- Regional pricing: N/A (free offline).

### Accessibility
- **Colorblind mode:** Full Okabe-Ito palette substitution, toggled in settings. Ensures all 8 colors are distinguishable for deuteranopia, protanopia, and tritanopia.
- **Keyboard control:** Number keys 1–N for palette colors; Tab navigation for buttons.
- **Touch targets:** Color buttons minimum 48×48 px per WCAG 2.5.5 (AAA) / Material guidelines.
- **Text scaling:** HUD uses rem units; scales with browser font size.
- **Reduced motion:** CSS @media (prefers-reduced-motion: reduce) disables sweep and confetti animations, retains instant color change.
- **High contrast:** Cell borders visible; colors chosen for sufficient contrast against neutral background.
- **Age/content rating:** ESRB E / PEGI 3 — no violence, no mature content, no data collection. No COPPA/GDPR-K concerns for this offline clone.

---

## 14. Technical Structure

### Engine & language
- **Vanilla HTML5 + CSS3 + JavaScript (ES6+).** No framework, no build step.
- **DOM grid** (table or div grid) preferred over Canvas for easier CSS animation and accessibility; Canvas also viable for larger grids.
- Single `.html` file; all styles and scripts inline.

### Platforms
- Any modern browser (Chrome, Firefox, Safari, Edge) — desktop and mobile.
- Fully offline; no network requests after load.

### Save system
- **localStorage** keyed by config string (e.g., `"flood_best_14_6"` for 14×14 / 6 colors).
- Stores: best move count (integer).
- No accounts, no cloud sync, no auth.

### Multiplayer / netcode
- None. Single-player only.

### Anti-cheat
- N/A. Offline puzzle game with no server.

### Flood-fill algorithm
- **BFS (iterative)** from cell [0,0] to find all connected same-color cells = current flood region.
- On move: change all region cells to new color; then expand BFS from region boundary to absorb adjacent new-color cells. Iterative (queue-based), not recursive — avoids stack overflow on large grids. [Confirmed — critical for correctness]

### Performance
- 16×16 = 256 cells — trivial for DOM manipulation.
- Animation: CSS class toggle + requestAnimationFrame for flood wave timing.
- No heavy computation; even solver (if included) on 16×16 with 8 colors runs in <10 ms greedy.

### App size
- Target: < 30 KB total (single HTML file, no images). [Estimated]

---

## 15. Pacing & Difficulty

### Early game (first 1–5 sessions)
- Player likely picks colors somewhat randomly, losing occasionally.
- Mechanic is immediately clear; "aha moment" within first game when they realize targeting large adjacent regions is the key strategy.
- 8×8 / 4 colors serves as a natural warm-up config.

### Mid game (5–20 sessions)
- Player develops region-counting intuition — identifies which color pick merges the most disconnected blobs.
- Consistent wins on 14×14 / 6 colors; starts trying to lower personal best.
- Move limit of 25 feels achievable (most players finish ~20–23).

### Late game / mastery
- Player attempts 16×16 / 8 colors for the hardest challenge.
- Optimal-play: think 2–3 moves ahead; prioritize colors that also free up "chains" (same-color regions touching each other through a shared gap).
- Expert target: solve 14×14 / 6 in 18–20 moves [Confirmed].

### Churn points
- Losing on a particularly unlucky board (colors very fragmented) can feel unfair — mitigate by ensuring move limit is set generously enough (~25 for 14×14/6).
- Hitting personal-best plateau can reduce motivation — the daily-challenge optional feature addresses this.

---

## 16. Clone Build Plan

### MVP vs. full feature checklist
| Feature | MVP | Full |
|---------|-----|------|
| 14×14 / 6-color random board | Yes | Yes |
| Flood-fill from top-left on color pick | Yes | Yes |
| Move counter + move limit | Yes | Yes |
| Win / lose detection + overlay | Yes | Yes |
| New Game button | Yes | Yes |
| Color palette (tap + keyboard 1–N) | Yes | Yes |
| Active flood color highlight | Yes | Yes |
| Personal best (localStorage) | Yes | Yes |
| Responsive / mobile-first layout | Yes | Yes |
| Grid size + color count options | Yes | Yes |
| Flood sweep animation | No | Yes |
| Win confetti + SFX | No | Yes |
| Colorblind palette | No | Yes |
| Percent-flooded indicator | No | Yes |
| Mute toggle | No | Yes |
| Settings panel | No | Yes |
| Reduced-motion support | No | Yes |

### Phased roadmap

**Phase 1 — Core engine (1–2 days)**
1. HTML skeleton: viewport meta, CSS reset, grid container, palette bar, HUD.
2. Board generation: 2D array, random color assignment, render to DOM grid.
3. BFS flood-fill: iterative queue, finds all connected cells from [0,0].
4. Move handler: on color pick → run fill → update board → increment counter → check win/lose.
5. Win/lose detection + simple text overlays.

**Phase 2 — Polish & options (1 day)**
6. Grid size + color count selectors → rebuild board on change.
7. Personal best stored and displayed.
8. Keyboard shortcuts (1–8).
9. Active color button highlight.
10. Responsive sizing (CSS calc for cell size based on viewport and grid size).

**Phase 3 — Juice (0.5–1 day)**
11. Flood sweep animation (staggered CSS class adds to newly absorbed cells).
12. Win confetti particles (CSS-only or lightweight canvas).
13. WebAudio SFX (flood pop, win chime, lose tone).
14. Mute toggle.
15. Colorblind palette toggle.
16. Percent-flooded live indicator.

**Phase 4 — QA & delivery**
17. Test all grid/color combos, win/lose, no-op, keyboard nav, mobile touch.
18. Verify no console errors, valid HTML5, no infinite loop edge cases.
19. Minify if desired (not required for single HTML).

### Recommended tech stack
- Pure HTML5 / CSS3 / ES6 JavaScript — no dependencies.
- DOM grid (div-based with CSS grid) for easy animation and accessibility.
- WebAudio API for generated SFX.
- localStorage for persistence.

### Required asset list
- No external assets. All colors, shapes, and sounds are generated in code.
- Optional: one web-safe icon/favicon (inline SVG data URI).

### Hardest parts / risks
1. **Flood-fill correctness:** Must be iterative BFS (not recursive) to handle 16×16 without stack overflow. The "same color no-op" edge case must be handled before BFS runs.
2. **Move-limit tuning:** The limit must be generous enough that ~70% of players can win on normal (14×14/6) with reasonable play. 25 is confirmed; other configs need careful estimation.
3. **Flood sweep animation performance:** On 16×16 with 8 colors, a single move may absorb 50+ cells. Staggering 256 DOM transitions must not cause jank — use CSS class batching + rAF, not per-cell JS timers.
4. **Responsive sizing:** The grid must fill the viewport on both 320px iPhone SE and 1440px desktop without overflow or excessive whitespace.
5. **Win detection timing:** Win check must fire AFTER the BFS expansion is complete (after all newly absorbed cells are counted), not mid-animation.

---

## 17. Open Questions

1. **Optimal move limit for 10×10/7 and 12×12/7 configs** — the values in the table are estimated from interpolation; could be calibrated by running a greedy solver 1000× per config and setting limit = mean + 1 SD. [Estimated; tune in playtesting]
2. **Flood sweep animation style** — ripple outward from [0,0] vs. simultaneous flash of all new cells vs. stagger by Manhattan distance from boundary. All are valid; stagger-by-distance looks best but adds ~20 lines of code. [Design choice]
3. **Daily challenge mode** — seeding by date so players compete on the same board is a high-value retention feature but requires a date-to-seed RNG (simple: mulberry32 with seed = yyyymmdd integer). Left as optional Phase 5.
