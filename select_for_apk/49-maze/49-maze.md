# Maze (Auto-Generate + Play) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not any original name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

A procedurally-generated perfect-maze navigation game. Each level generates a new, unique maze using the Recursive Backtracker (DFS) algorithm, guaranteeing exactly one path between any two cells (a "perfect" maze). The player spawns at the top-left and must reach the exit at the bottom-right. Levels increase maze size, ramping difficulty. A BFS-powered hint/solve button reveals the optimal path. The core appeal is the satisfying feeling of navigating tight corridors, solving spatial puzzles, and chasing personal-best times.

**Quick facts:**
- Developer: Original (this spec) | Platform: Web (HTML5 Canvas) | Release: New build
- Genre: Maze / Puzzle | Sub-genre: Procedural Navigation | Rating: E (Everyone) [Estimated]
- Monetization: None (free, offline single-player) | Session length: 1–5 min per maze
- Comparable games: Amazing Maze, A-Maze-ing, Labyrinth (classic), PacMaze [Confirmed genre refs]

---

## 2. Core Loops

- **30-second loop:** Player presses arrow key / swipe direction → token moves one cell in that direction → wall collision blocks invalid moves → step counter increments → player visually advances toward exit → brain engages spatial navigation.
- **Session loop:** Start screen → select difficulty / maze size → maze generates instantly → player navigates with timer running → reaches exit → win overlay shows time + moves → compare to personal best → tap "Next Level" (bigger maze) or "New Maze" (same size) → repeat.
- **Meta loop:** Best time and best move-count saved per size tier in localStorage → player chases personal records → each level increase in size resets the challenge ceiling → optional hint use tracked (penalized or noted) → mastery arc across Small → Medium → Large → XL → XXL sizes.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
- **Maze generation:** Recursive Backtracker (DFS) algorithm creates a perfect maze — every cell reachable, exactly one path between any two cells, no isolated regions. [Confirmed algorithm]
- **Wall representation:** Each cell stores 4 boolean walls (N, E, S, W). Removing a wall between two cells carves a passage. Grid stored as a flat array of cell objects.
- **Player movement:** One cell at a time. Input checked against the current cell's wall state before moving. Illegal moves (into a wall) produce a visual/audio "bump" feedback but do NOT advance.
- **Exit detection:** Player position equals exit cell (bottom-right corner) → trigger win state.
- **Hint system:** BFS from player's current position to exit → reveals the solution path as a highlighted trail on the canvas. Path updates if the player moves off-track. Hint use is tracked (shown in win overlay).
- **Solve mode:** Full BFS path from start to exit highlighted permanently until dismissed.
- **Timer:** Starts on first move, stops on exit reached. Displayed live in HUD.
- **Move counter:** Increments on every successful (non-wall) move.
- **Camera / viewport:** For large mazes, a viewport camera follows the player (canvas pans/zooms so player stays centered), preventing the entire maze from being too small to navigate on mobile.

### Core Verbs
Move, Turn, Bump (wall), Reveal hint, Reset, Navigate to exit.

### Game Modes
| Mode | Description |
|------|-------------|
| Normal Play | Navigate from start to exit at own pace |
| Timed Challenge | Same as normal, but best-time leaderboard tracked |
| Hint/Solve | BFS path revealed on demand |
| Auto-Solve Demo | Entire BFS path animated step-by-step (watch mode) |

### Controls
| Action | Keyboard | Mobile |
|--------|----------|--------|
| Move Up | Arrow Up / W | Swipe Up / D-pad Up button |
| Move Down | Arrow Down / S | Swipe Down / D-pad Down button |
| Move Left | Arrow Left / A | Swipe Left / D-pad Left button |
| Move Right | Arrow Right / D | Swipe Right / D-pad Right button |
| Hint | H key | "Hint" button |
| New Maze | R key | "New" button |
| Solve | N/A | "Solve" button |

- **Orientation:** Portrait (mobile-first); landscape also supported with wider canvas.
- **Swipe detection:** touchstart/touchend delta ≥ 30px determines direction on the canvas area.
- **On-screen D-pad:** Four directional buttons rendered below maze canvas on mobile.

### Win / Lose / Fail States
- **Win:** Reach exit → overlay displays time, moves, hint count, personal best comparison.
- **No lose state:** Player cannot die or fail; maze is always solvable.
- **Soft fail:** Using hint adds a "Hint Used" flag to the win summary; using Solve marks the run as assisted.
- **Stuck prevention:** Hint always available; BFS guarantees path exists.

### AI / Difficulty
- No AI opponent. Difficulty = maze size (cell count). Larger = more corridors = harder navigation.
- Five size tiers: Small (11×11), Medium (15×15), Large (21×21), XL (31×31), XXL (41×41).

### Feedback Systems
- **Visual:** Player token smoothly slides between cells (CSS/canvas interpolation). Wall bump flashes cell red briefly. Win state: gold particle burst at exit cell.
- **Audio (WebAudio, no files):** Step tone (soft click), wall bump (short low thud), win fanfare (ascending chord), hint reveal (soft whoosh). Mute toggle available.
- **Haptic:** Not implemented (web limitation). [Estimated]

---

## 4. Progression

### Level Scaling
| Level | Maze Size | Cell Count | Approx. Corridor Length |
|-------|-----------|------------|------------------------|
| 1–3   | 11×11     | 121        | Short (tutorial-feel)  |
| 4–6   | 15×15     | 225        | Medium                 |
| 7–10  | 21×21     | 441        | Challenging            |
| 11–15 | 31×31     | 961        | Hard                   |
| 16+   | 41×41     | 1681       | Expert                 |

- Each level within a tier gets a freshly generated maze (different seed).
- After level 3 within a tier, the next tier unlocks automatically.
- Players can also select size manually from the options panel.
- No XP, no unlock trees — progression is purely maze complexity and personal best records.

### Personal Best System
- Best time (seconds) per size tier saved to localStorage.
- Best move count per size tier saved to localStorage.
- "New Record!" banner shown on win overlay when beaten.
- Hint-assisted runs tracked separately (do not overwrite unaided best).

### No Prestige / No Gating
- All size tiers accessible from the options menu at any time. [Estimated design choice]
- No energy, no lives, no paywalls.

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| None | — | — | — |

This game has no currency economy. Progression is skill-based only.

### Cost Scaling
No upgrade costs. N/A.

### RNG
| Element | RNG Mechanism | Notes |
|---------|--------------|-------|
| Maze layout | DFS recursive backtracker with `Math.random()` | New seed each generation [Confirmed] |
| Exit position | Fixed: bottom-right corner | Deterministic [Estimated] |
| Start position | Fixed: top-left corner | Deterministic [Estimated] |
| Wall carving order | Neighbors shuffled randomly each DFS step | Produces varied corridor shapes [Confirmed] |

No gacha, no loot tables, no drop rates. RNG is structural (maze topology only).

---

## 6. Content Inventory *(counts + lists)*

### Maze Sizes
5 tiers × infinite procedural generation = unlimited unique mazes [Confirmed by algorithm]

### Themes
| Theme Name | Wall Color | Floor Color | Player Color | Notes |
|------------|-----------|-------------|--------------|-------|
| Classic | #2c3e50 (dark blue) | #ecf0f1 (light grey) | #e74c3c (red) | Default |
| Night | #1a1a2e (deep navy) | #16213e (dark blue) | #00d4ff (cyan) | Unlocked at level 5 |
| Forest | #2d5a27 (dark green) | #f0e6d3 (sand) | #ff6b35 (orange) | Unlocked at level 10 |

(Themes are cosmetic only; no gameplay effect.)

### UI Screens
- Start screen, Game screen, Win overlay, Settings panel (in-game).

### Audio
- 4 WebAudio procedural SFX (step, bump, hint, win); 1 ambient optional pad (off by default).

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract / geometric. Clean grid-based labyrinth with no specific fictional world.
- **Premise:** No story. Pure puzzle challenge. "Can you navigate the maze faster than before?"
- **Tone:** Calm, focused, slightly competitive (personal bests). Not tense or stressful.
- **Visual style:** Clean, modern, minimal. Slight drop-shadow on walls; smooth token animation.
- **Writing style:** Minimal UI text. Terse labels. No dialogue. Numbers + icons communicate.
- **Color palette:** Chosen per theme; default is dark-wall / light-floor / red-player / gold-exit.
- **No licensed IP.** Entirely original.

---

## 8. Meta & Social Systems

### Records & Stats
- Personal best time + moves per size tier (localStorage).
- Current session: level, total mazes completed, total moves, total hints used.

### No Social Systems
- No leaderboards (web/offline, no backend).
- No multiplayer, no guilds, no PvP.
- No daily rewards, no battle pass.

### Live-ops Cadence
- N/A — static offline web game. No content updates required post-launch.
- Optional: add a "Daily Maze" mode using a date-seeded RNG for a shared daily puzzle. [Estimated]

### Retention Hook (Light)
- "Beat your best time" loop is the primary retention driver.
- Level counter creates a sense of progress.

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| Start Screen | Title, size selector, "Play" button, brief how-to |
| Game Screen | Active maze canvas, HUD, D-pad, action buttons |
| Win Overlay | Time/moves result, personal best comparison, Next/Retry buttons |
| Settings Panel | Mute toggle, theme selector, size selector, reset records |

### Gameplay HUD Elements
- **Level number** (top-left)
- **Timer** (top-center, counting up from 0:00)
- **Move counter** (top-right)
- **"Hint" button** (below canvas, left)
- **"Solve" button** (below canvas, center)
- **"New Maze" button** (below canvas, right)
- **Best time indicator** (below HUD bar)
- **Mute icon** (top-right corner)

### Navigation Flow
```
Start Screen → [Play] → Game Screen → [Win] → Win Overlay → [Next Level] → Game Screen
                                                           → [Same Size] → Game Screen
                                                           → [Menu] → Start Screen
Game Screen → [Settings icon] → Settings Panel → [Close] → Game Screen
```

### Onboarding / Tutorial (Step-by-Step)
1. First launch: Start screen shows title + "How to Play" text (arrow keys / swipe to move, reach the exit).
2. First maze generated: A brief animated arrow points from player token toward the first open corridor (one-time, 2 seconds, then disappears).
3. If player hasn't moved in 8 seconds: subtle pulse animation on the player token draws attention.
4. If player hasn't used hint: small "Press H for hint" tooltip appears after 30 seconds on first maze.
5. Win overlay on first win: explains personal best tracking.
6. No forced tutorial interruptions after first session.

### Settings / Options Menu
- Sound: On / Off toggle
- Theme: Classic / Night / Forest
- Maze Size: Small / Medium / Large / XL / XXL
- Reset Records: Clears all localStorage personal bests (with confirmation)
- Version info / credits

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D top-down** grid view. [Confirmed for web maze games]
- **Camera:** For mazes ≤ 21×21, full maze fits in canvas. For 31×31 and 41×41, viewport follows player (canvas clips to a window, pans smoothly so player stays centered). [Estimated, industry standard for large mazes]
- **Orientation:** Portrait-first mobile, landscape responsive on desktop.

### Art Style
- Flat / geometric. No sprites, no textures — pure canvas-drawn rectangles and lines.
- Walls: thick rounded lines or filled rectangles, slight shadow.
- Player: colored circle with subtle inner glow; animated smooth slide (lerp, ~100ms).
- Exit: pulsing gold square / star marker.
- Hint path: semi-transparent colored trail overlay (e.g., yellow-green dots or line).
- Grid background: solid fill; visited cells during generation optionally shown (animation mode).

### Color Palette (Default — Classic)
| Element | Color |
|---------|-------|
| Background | #ecf0f1 |
| Walls | #2c3e50 |
| Player | #e74c3c |
| Exit | #f1c40f (gold) |
| Hint path | rgba(46, 204, 113, 0.5) (green) |
| Solved path | rgba(52, 152, 219, 0.6) (blue) |
| HUD background | #34495e |
| HUD text | #ecf0f1 |

### Animation & VFX
- Player token: lerp position interpolation (smooth slide, not teleport).
- Wall bump: brief red flash on the blocking wall segment.
- Win: gold particle burst (canvas confetti, ~40 particles) + token pulse.
- Hint path: fade-in animation over 0.3s.
- Level transition: quick canvas fade-out → new maze fade-in.

### Audio (WebAudio API, zero external files)
| Sound | Trigger | Description |
|-------|---------|-------------|
| step | Successful move | Short soft click (sine wave, 220Hz, 80ms) |
| bump | Wall collision | Low dull thud (sawtooth, 80Hz, 60ms) |
| hint | Hint revealed | Soft ascending whoosh (filtered noise, 200ms) |
| win | Exit reached | Ascending 3-note fanfare (C-E-G, 500ms) |
| new_maze | New maze generated | Soft UI pop (sine, 440Hz, 100ms) |

Mute toggle silences all AudioContext output instantly.

---

## 11. Monetization

**None.** This is a free, offline, no-ads, no-IAP web game. [Estimated design intent]
- No ad placements, no consent/ATT/CMP flows required.
- No IAP product table.
- No loot boxes, gacha, or subscriptions.
- No data collection → no GDPR/COPPA considerations required.

---

## 12. Retention Hooks

### Personal Best Chase
- Best time and move count per size tier drives re-play motivation.
- "New Record!" feedback on win creates dopamine loop. [Estimated]

### Level Progression
- Level counter increments each maze completed; acts as a soft "score."
- Larger mazes unlock a fresh challenge ceiling.

### No Offline Earnings
- Game has no idle/offline earnings mechanic (active play-only). [Confirmed by design]

### No Push Notifications
- Web game; no notification system required.

### No Energy / Lives
- Unlimited plays; no gating on session length.

---

## 13. Localization & Accessibility

### Language
- English only (minimal text UI). [Estimated — easy to add more]
- All UI text short enough for simple i18n string-swap if needed.
- No RTL text; no complex typographic requirements.

### Accessibility
| Feature | Implementation |
|---------|---------------|
| Keyboard navigation | Full arrow + WASD support |
| Touch / mobile | Swipe + D-pad |
| Color blind mode | High-contrast theme option (optional future) |
| Font size | Large, readable HUD labels (min 14px) |
| No flashing hazards | No strobing effects |
| Pause anytime | New Maze button = instant reset |

### Age / Content Rating
- ESRB: E (Everyone). No violence, no mature content. [Estimated]
- COPPA: Not applicable (no data collection, no accounts).
- GDPR: Not applicable (no cookies, no tracking, no backend).

### Regional Differences
- None. Fully offline.

---

## 14. Technical Structure

### Engine & Stack
- **Vanilla HTML5 / JavaScript / Canvas 2D API** — no framework, no build step. [Confirmed design]
- Single `.html` file with inline `<style>` and `<script>` tags.
- Runs entirely client-side; no server, no CDN, no network requests.

### Maze Generation
- **Algorithm:** Recursive Backtracker (DFS with explicit stack to avoid call-stack overflow on large mazes). [Confirmed best practice]
- **Data structure:** Array of cell objects `{ walls: [N,E,S,W], visited: bool }`.
- **Guaranteed solvable:** Algorithm's property — all cells connected, perfect maze always produced.

### BFS Solver
- **Algorithm:** Breadth-First Search from any cell to exit.
- **Input:** Current player position (or maze start).
- **Output:** Array of cell indices forming shortest path.
- **Use:** Hint (partial reveal from current position) and Solve (full path from start).

### Save System
- **localStorage** keys:
  - `maze_best_time_<size>` — float seconds
  - `maze_best_moves_<size>` — integer
  - `maze_settings` — JSON: { theme, size, muted }
- No accounts, no cloud sync, no auth.

### Performance
- Cell size calculated from canvas dimensions ÷ maze grid size → auto-scales.
- Viewport camera for large mazes: only draws visible cells + margin (culling). [Estimated]
- Generation is synchronous (iterative DFS) — runs in < 5ms for 41×41. [Estimated]
- Targeting 60fps during gameplay.

### Browser Compatibility
- Chrome, Firefox, Safari, Edge (all modern). Canvas 2D + WebAudio supported universally.
- No ES6 modules (plain `<script>`); no transpile needed.
- File size: ~40–70KB total (pure code, no assets).

### Anti-cheat / Multiplayer
- N/A — single-player, offline only.

---

## 15. Pacing & Difficulty

### Early Game (Levels 1–3, 11×11)
- Short mazes, ~20–60 moves to solve.
- Player learns controls; discovers hint system.
- "Aha" moment: solving first maze quickly.

### Mid Game (Levels 4–10, 15×15 → 21×21)
- Longer corridors, more dead ends.
- Players learn to mentally track dead ends visited.
- "Aha" moment: finishing without hint under personal best.

### Late Game (Levels 11–15, 31×31)
- Complex branching paths; spatial memory tested.
- Hint usage rises unless player is skilled.
- "Aha" moment: flawless run on a 31×31.

### Expert (Level 16+, 41×41)
- Large viewport-panned maze; challenging orientation.
- Longest runs: 3–8 minutes typical. [Estimated]
- Camera panning is a new cognitive load layer.

### Churn Points
- Players who get lost repeatedly in Large+ may quit without hint encouragement.
- "Stuck" detection (no move in 15s) → subtle hint prompt reduces abandonment. [Estimated]
- Maze visual clarity (wall thickness, contrast) is critical for mobile retention.

---

## 16. Clone Build Plan

### MVP Feature Checklist
- [x] Recursive backtracker maze generation (all 5 sizes)
- [x] Player token movement with wall collision
- [x] Timer + move counter
- [x] Exit detection + win overlay
- [x] BFS hint / solve path reveal
- [x] Personal best localStorage persistence
- [x] Mobile swipe + D-pad controls
- [x] Keyboard (arrow + WASD)
- [x] Responsive canvas scaling
- [x] WebAudio SFX + mute toggle

### Full Feature Checklist (beyond MVP)
- [ ] Multiple visual themes
- [ ] Viewport camera for large mazes
- [ ] Particle win effects
- [ ] Smooth player animation (lerp)
- [ ] Daily maze mode (date seed)
- [ ] Hint-use tracking in personal best
- [ ] Animated generation visualization (watch mode)
- [ ] High-contrast / colorblind mode

### Phased Roadmap
| Phase | Deliverables |
|-------|-------------|
| Phase 1 | Grid data structure, DFS maze generator, canvas renderer (walls + player) |
| Phase 2 | Keyboard + swipe controls, wall collision, exit detection, win state |
| Phase 3 | BFS solver, hint overlay, solve animation |
| Phase 4 | Timer, move counter, personal best localStorage |
| Phase 5 | Mobile D-pad, viewport camera for large mazes, responsive scaling |
| Phase 6 | WebAudio SFX, mute toggle, visual polish (themes, particles, animations) |
| Phase 7 | Start screen, settings panel, onboarding, QA pass |

### Recommended Tech Stack
- **Language:** Vanilla JavaScript (ES2020+), no frameworks
- **Rendering:** HTML5 Canvas 2D API
- **Audio:** Web Audio API (procedural synthesis, no audio files)
- **Storage:** localStorage
- **Build:** None — single HTML file

### Required Asset List
| Asset | Type | Source |
|-------|------|--------|
| Maze walls | Canvas draw calls | Generated in code |
| Player token | Canvas circle | Generated in code |
| Exit marker | Canvas rect + glow | Generated in code |
| Hint/solve path | Canvas line | Generated in code |
| SFX (5 sounds) | WebAudio synthesis | Generated in code |
| Fonts | System sans-serif | No external font needed |

**No external assets required.**

### Hardest Parts / Risks
1. **Large maze camera viewport:** Correctly clipping canvas draw region + smooth pan without performance stutter on mobile. Use transform/translate approach.
2. **DFS stack depth for large mazes:** Iterative (non-recursive) DFS required for 41×41 to avoid call-stack overflow. Must use explicit stack array.
3. **Mobile swipe precision:** Distinguishing swipe from scroll on the canvas element; `preventDefault()` on touch events required but may conflict with page scroll.
4. **BFS path correctness:** Hint path must re-run from *current* player position, not maze start — easy to misimplied.
5. **Canvas scaling across devices:** Cell size must recalculate on window resize; player position must remain correct in world-space vs. screen-space coordinates.

---

## 17. Open Questions

1. **Daily Maze mode:** Would use `new Date()` date string as RNG seed; requires a seeded PRNG (e.g., mulberry32) to produce consistent maze from the same seed — minor addition but not in MVP. [Estimated]
2. **Haptic feedback on mobile:** Web Vibration API is supported on Android Chrome but not iOS Safari; if desired, simple `navigator.vibrate(20)` on wall-bump for Android. [Confirmed platform limitation]
3. **Colorblind mode:** Specific palette not designed yet; a toggle that switches to high-contrast black/white with pattern fills on the hint path would serve deuteranopia users adequately. [Estimated]
4. **Maze generation speed visualization:** If "watch generation" mode added, need requestAnimationFrame step-by-step reveal; adds ~50 lines, low risk. [Estimated]

---

*Blueprint complete. All 17 sections filled. Economy = none (stated explicitly). Win/fail = defined. Screen map = complete. No TBDs outside Open Questions. Build-ready.*
