# Connect Four — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Connect Four is a two-player abstract strategy board game in which players alternate dropping colored discs into a vertically-suspended 7-column × 6-row grid, where discs fall to the lowest available slot in the chosen column. The first player to form an unbroken line of four of their own discs — horizontally, vertically, or diagonally — wins. If all 42 slots are filled with no winner, the game is a draw. The game's appeal is its instantly-graspable rules, 30-second learnability, and surprisingly deep strategy.

**Quick facts:**
- Developer/Publisher: Milton Bradley (original 1974 board game); digital versions by numerous studios
- Platforms: Physical board game; web, iOS, Android, PC (many independent implementations)
- Release: 1974 (board game); digital versions from 1980s onward
- Age/Content Rating: ESRB E (Everyone) / PEGI 3 [Confirmed]
- Monetization model (this clone): Free to play, no monetization; optional WebAudio SFX
- Comparable games: Tic-Tac-Toe, Gomoku (Five-in-a-Row), Othello/Reversi, Checkers

---

## 2. Core Loops

- **30-second loop:** Player clicks/taps a column → disc animates falling to lowest available row → win/draw detection fires → turn switches to opponent (or AI responds in ~0.3–1 s).
- **Session loop:** Players select mode (vs AI / 2-player) and difficulty → play one round → see result banner with winner highlight → reset for another round → running scoreboard accumulates across rounds.
- **Meta loop:** Scoreboard persists in localStorage across browser sessions; players return to settle win-loss standings or to challenge themselves on Hard AI.

---

## 3. Mechanics, Controls & Game States

### Core Rules [Confirmed]
- Board: 7 columns × 6 rows = 42 total cells.
- On each turn the active player selects a column; the disc falls via gravity to the lowest empty row in that column.
- A column is "full" when all 6 rows are occupied; selecting a full column is ignored.
- Win: four consecutive discs of the same color in a horizontal, vertical, or diagonal (both directions) line.
- Draw: all 42 cells filled with no winner.

### Core Verbs
- **Drop**: click/tap a column header or drop-button to place a disc.
- **Hover (desktop)**: preview disc appears at top of column before click.

### Game Modes
| Mode | Description |
|------|-------------|
| vs AI | One human (Red) vs computer (Yellow); AI difficulty selectable |
| 2-Player Local | Two humans share device; Player 1 = Red, Player 2 = Yellow |

### Difficulty Levels (vs AI mode)
| Level | Algorithm | Search Depth | Behavior |
|-------|-----------|-------------|----------|
| Easy | Random + minimal threat check | N/A | Picks random legal column; will block an immediate 4-in-a-row threat ~50% of the time |
| Medium | Minimax (no pruning) | 4 | Blocks opponent wins, takes own wins, some strategic play |
| Hard | Minimax + alpha-beta pruning | 7 | Full strategic play; blocks/takes wins instantly, uses center-column heuristic |

### Input Scheme
- **Desktop**: mouse hover to preview disc in column → left-click to drop; or click column-top drop button.
- **Mobile**: tap column area or tap drop button above column.
- Drop-buttons row sits above the board for reliable touch targets.
- Screen orientation: portrait on mobile, landscape/any on desktop.

### Win / Lose / Draw States
- **Win**: winning 4-disc line is highlighted (flashing animation); banner shows winner name; scores updated.
- **Draw**: banner shows "Draw!"; no score awarded.
- **New Game**: reset board, keep scores; mode and difficulty preserved.

### AI Behavior (Hard)
- AI uses minimax with alpha-beta pruning [Confirmed].
- Evaluation: scores all windows of 4 cells in all directions.
- Score table per window [Estimated from standard implementations]:
  - AI has 4 in window: +100,000 (winning)
  - AI has 3 + 1 empty: +100
  - AI has 2 + 2 empty: +10
  - Opponent has 3 + 1 empty: -120 (block threat weighted slightly above own 3)
  - Center column preference: +6 per AI disc in center column
- Move ordering: center columns searched first (col 3, then 2, 4, then 1, 5, then 0, 6) for better pruning.
- Depth 7 at branching factor ~7 handles typical mid-game within ~200 ms in modern JS [Estimated].

### Feedback Systems
- Drop animation: disc "falls" from top to target row (~15–25 ms per row traversed).
- Winning disc flash: the 4 winning cells pulse/flash 3× in distinct color.
- Sound effects (WebAudio): disc drop click, win fanfare, draw tone, button click.
- Mute toggle persists to localStorage.

---

## 4. Progression

Connect Four has no persistent progression system in its classic form [Confirmed]. The clone uses:
- **Scoreboard** as light progression: tracks Win / Loss / Draw counts per player per session, persisted to localStorage.
- **Difficulty advancement**: players self-select from Easy → Medium → Hard as skill grows.
- No XP, no unlock trees, no prestige mechanics — appropriate for this game's genre.

---

## 5. Economy & RNG

### Currencies
| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| None | — | — | — |

Connect Four has no economy [Confirmed]. No soft currency, hard currency, or premium currency.

### RNG
- **Easy AI**: random column selection (uniform random over legal columns) [Confirmed by design].
- **Medium/Hard AI**: deterministic minimax; no RNG.
- No loot, no drops, no gacha.

### Cost Scaling
- N/A — no upgrades, no purchases.

---

## 6. Content Inventory

| Category | Count | Notes |
|----------|-------|-------|
| Board configurations | 1 | 7×6 fixed |
| Disc colors | 2 | Red (Player 1), Yellow (Player 2/AI) |
| Game modes | 2 | vs AI, 2-Player Local |
| Difficulty levels | 3 | Easy, Medium, Hard |
| Win line directions | 4 | Horizontal, vertical, diagonal ↗, diagonal ↘ |
| Total possible board states | ~4.5 trillion | Confirmed by combinatorics literature |

No characters, items, enemies, levels, or collectibles — the game is pure abstract strategy [Confirmed].

---

## 7. Theme, Narrative & Tone

- **Setting**: Abstract; classic blue plastic board aesthetic.
- **Premise**: Pure competitive abstract strategy — no narrative.
- **Story delivery**: None.
- **Tone**: Clean, competitive, friendly. Classic toy/game aesthetic.
- **Color palette**: Deep blue board (#1a3a6b), red disc (#e74c3c), yellow disc (#f1c40f), dark background (#0d1b2a), white text.
- **Licensed IP**: Original game is Milton Bradley / Hasbro IP. Clone must use its own name, colors may differ from exact Hasbro trade dress. Mechanics are not copyrightable [Confirmed — mechanics are in public domain].

---

## 8. Meta & Social Systems

- No daily missions, quests, or battle pass.
- No live-ops; no seasonal content.
- **Leaderboard**: local session scoreboard only (Win/Loss/Draw per player, persisted to localStorage).
- **Multiplayer**: 2-player local (same device) only in this clone; no online multiplayer.
- No guilds, clans, referral, or sharing features in the base implementation.
- No staff management, base building, or decoration.

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| Main / Game Screen | Single-page app: all UI lives here |
| Mode Selector | Toggle between "vs AI" and "2-Player Local" |
| Difficulty Selector | Easy / Medium / Hard (visible only in vs AI mode) |
| Board | 7×6 interactive grid + drop-button row above |
| Turn Indicator | Shows whose turn it is + disc color swatch |
| Scoreboard | Running Win/Loss/Draw for both players |
| Result Banner | Overlay or top-banner showing winner/draw |
| How to Play | Collapsible rules section below board |
| Settings | Mute/unmute toggle (inline, no separate screen) |

### Settings / Options Contents
- Sound on/off toggle (mute all WebAudio)
- Mode: vs AI / 2-Player Local
- Difficulty: Easy / Medium / Hard
- New Game button
- Score reset button (long-press or dedicated button)

### Gameplay HUD Elements
- Column drop-buttons row (7 buttons above columns)
- Board grid (7×6 cells with disc fill)
- Disc preview (hover ghost disc, top of column)
- Turn indicator (top: "Red's Turn" / "Yellow's Turn" with colored disc icon)
- Scoreboard (persistent, always visible)
- Result banner (shown on win/draw, hidden during play)
- New Game button
- Mute toggle icon

### Navigation Flow
```
Page Load → Read localStorage (scores, settings) → Show Game Screen
Game Screen [Mode Select] ↔ [Difficulty Select] → [Play] → [Result Banner] → [New Game] → [Play]
```

### Onboarding / Tutorial (first-time flow)
1. Page loads; "How to Play" section visible below board with collapse toggle.
2. Step 1 shown: "Click a column (or tap the ▼ button above it) to drop your disc."
3. Step 2: "Discs fall to the bottom. Get 4 in a row — across, up/down, or diagonal — to win!"
4. Step 3: "If the board fills with no winner, it's a Draw."
5. No forced tutorial; players can click New Game and start immediately.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D** top-down/orthographic; no 3D camera [Confirmed].
- Layout: board centered, portrait-friendly on mobile.

### Art Style
- Flat / clean design; CSS-only rendering (no canvas required, but canvas acceptable).
- **Board**: deep blue (#1a3a6b) rounded rectangle; circular holes for each cell.
- **Red disc**: #e74c3c with subtle radial gradient highlight (top-left lighter).
- **Yellow disc**: #f1c40f with matching highlight.
- **Empty cell**: dark navy/black circle (#0d1b2a) inside blue board.
- **Winning disc**: pulse animation — scale 1 → 1.15 → 1 at 0.4 s interval, color brightens.
- Board drop-shadow for depth.

### Animation
- **Drop animation**: disc moves from row 0 downward, one step per 40 ms (fast, satisfying).
- **Win flash**: CSS keyframe on winning 4 cells, 3× pulse.
- **Button hover**: slight scale-up on desktop (transform: scale(1.15)).

### VFX
- Particle burst on win (optional: 8–12 small circles scatter from winning discs).
- Screen shake (optional, subtle) on Hard AI move block.

### SFX (WebAudio — no files)
| Sound | Trigger | Description |
|-------|---------|-------------|
| drop_click | Disc placed | Short tick (oscillator, ~80 Hz, 0.1 s) |
| win_fanfare | Game won | 3-note ascending arpeggio (220→330→440 Hz) |
| draw_tone | Draw | Single descending tone (440→220 Hz, 0.4 s) |
| button_click | UI interaction | Short high tick (880 Hz, 0.05 s) |

- Music: none (silent by default beyond SFX).
- Mute toggle disables all audio.

---

## 11. Monetization

**None** — this clone is free, no ads, no IAP, no subscriptions [Confirmed by design brief].

- No ad placements.
- No IAP product list.
- No loot boxes or gacha.
- No consent/ATT/CMP flow required (no tracking, no ads).
- No age gate (ESRB E; no data collected).

---

## 12. Retention Hooks

- **Scoreboard persistence**: scores survive page refresh via localStorage — gives players a reason to return and settle standings [Estimated effective].
- **Difficulty ladder**: Easy → Medium → Hard provides a natural progression goal.
- **Quick rounds**: ~2–3 minute average round length encourages "one more game."
- No daily rewards, no push notifications, no energy system, no idle/offline earnings — none appropriate for this game type [Confirmed].

---

## 13. Localization & Accessibility

### Languages
- English only (base clone) [Estimated].
- All UI strings in a single JS constants object for easy translation.
- No RTL support needed for base version; can be added via `dir="rtl"` on container.

### Accessibility
- **Colorblind mode**: discs additionally differentiated by shape or label (R / Y) or pattern (solid vs striped) — important since Red/Yellow is a classic colorblind failure case [Estimated — not in original].
- **Keyboard navigation**: tab to column drop-buttons; Enter/Space to drop; arrow keys to change column.
- **Text scaling**: responsive layout scales with browser zoom.
- **High contrast**: board colors chosen for WCAG AA contrast on text.
- **ARIA labels**: board cells have `aria-label="Column N, Row M, [empty/red/yellow]"` for screen readers.

### Age/Content Rating
- ESRB: E (Everyone) [Confirmed].
- PEGI: 3 [Confirmed].
- COPPA/GDPR-K: No personal data collected; no accounts; no ads targeting children → no special compliance required beyond standard GDPR (no tracking at all in this clone).

---

## 14. Technical Structure

### Engine / Framework
- **Vanilla HTML5 + CSS3 + ES6 JavaScript** — no framework, no build step [Confirmed by design brief].
- Single self-contained `.html` file with inline `<style>` and `<script>`.

### Platform
- Browser (any modern browser: Chrome, Firefox, Safari, Edge).
- Offline-capable: no network requests; runs by double-clicking the file.
- Mobile-responsive via `<meta name="viewport">` and CSS flexbox/grid.

### Save System
- `localStorage` for:
  - `cf_scores`: `{p1Wins, p2Wins, draws}` object.
  - `cf_settings`: `{mode, difficulty, muted}` object.
- No cloud save, no accounts, no auth.

### Multiplayer
- Local (same device / same screen) only; no netcode, no matchmaking.

### Anti-Cheat / Server Authority
- N/A — single-device, no online play, no competitive ranking.

### Backend / Analytics
- None — fully client-side, no telemetry.

### SDKs
- None — zero external dependencies.

### Performance Notes
- Hard AI minimax depth 7 runs in ~50–200 ms in V8 JS engine [Estimated].
- File size target: < 60 KB unminified.

---

## 15. Pacing & Difficulty

### Game Pacing
- **Early game (turns 1–10)**: Center-column contest; few threats; AI at Hard focuses on center (column 3) first.
- **Mid game (turns 11–28)**: Threat networks form; double-threats (forcing positions) emerge; Hard AI anticipates 7 moves ahead.
- **Late game (turns 29–42)**: Board nearly full; available columns shrink; draws become possible.

### Difficulty Curve
| Phase | Easy | Medium | Hard |
|-------|------|--------|------|
| Turn 1–6 | Random | Blocks win/takes win | Full strategic |
| Turn 7–20 | Random | Depth-4 planning | Center+diagonal focus |
| Turn 21+ | Random | Still decent | Rarely loses |

### Human Player Difficulty Curve
- **Beginner → Easy AI**: learns drop mechanics, basic row-blocking.
- **Beginner → Medium AI**: learns to plan 2–3 moves ahead.
- **Intermediate → Hard AI**: must plan forced wins, double-threats, odd/even row strategy.

### Milestone / "Aha" Moments
- First win vs Easy AI.
- First time creating a double-threat (forcing win regardless of opponent).
- Beating Hard AI for the first time.

### Churn Points [Estimated]
- Players may disengage if Hard AI wins every game (perceived as unfair).
- Mitigation: clearly labeled difficulty; Easy mode always available.

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---------|-----|------|
| 7×6 board rendering | ✅ | ✅ |
| Disc drop + gravity | ✅ | ✅ |
| Win detection (all 4 directions) | ✅ | ✅ |
| Draw detection | ✅ | ✅ |
| Winning disc highlight | ✅ | ✅ |
| 2-Player local mode | ✅ | ✅ |
| AI Easy (random) | ✅ | ✅ |
| AI Medium (minimax depth 4) | ✅ | ✅ |
| AI Hard (minimax + alpha-beta depth 7) | ✅ | ✅ |
| Column-full guard | ✅ | ✅ |
| Drop animation | ✅ | ✅ |
| Scoreboard | ✅ | ✅ |
| New Game / reset | ✅ | ✅ |
| Mobile-responsive layout | ✅ | ✅ |
| localStorage persistence | ✅ | ✅ |
| Drop-button row (touch targets) | ✅ | ✅ |
| Hover preview | — | ✅ |
| WebAudio SFX | — | ✅ |
| Mute toggle | — | ✅ |
| Colorblind mode | — | ✅ |
| Keyboard navigation | — | ✅ |
| Win particle VFX | — | ✅ |
| How-to-play section | — | ✅ |

### Phased Build Roadmap

**Phase 1 — Core Engine (Day 1)**
1. HTML skeleton: viewport meta, board grid CSS, column buttons.
2. Board state: `board[6][7]` 2D array, `currentPlayer` variable.
3. `dropDisc(col)`: find lowest empty row, place disc, render.
4. Win detection: check all windows of 4 in 4 directions from last-placed disc.
5. Draw detection: check if `board` is full.
6. Turn switching; disable input during AI turn.

**Phase 2 — AI (Day 1–2)**
7. Easy AI: `Math.random()` over legal columns.
8. Evaluate window function: score a 4-cell window for minimax.
9. Score board function: iterate all windows, sum scores.
10. Minimax function with alpha-beta pruning, configurable depth.
11. Medium AI: minimax depth 4 (no pruning needed but add for consistency).
12. Hard AI: minimax depth 7 + alpha-beta.
13. Move ordering: try center columns first.

**Phase 3 — UI / UX (Day 2)**
14. Mode selector (vs AI / 2-Player).
15. Difficulty selector (Easy / Medium / Hard).
16. Turn indicator with disc color preview.
17. Scoreboard (Win/Loss/Draw, updated on result).
18. Result banner (win/draw message, overlay or top-bar).
19. New Game button.
20. How-to-play collapsible.

**Phase 4 — Polish (Day 2–3)**
21. Drop animation (CSS transition or JS step loop).
22. Winning disc flash animation.
23. Hover preview (ghost disc on column mouseover).
24. WebAudio SFX: drop, win, draw, button click.
25. Mute toggle + localStorage persistence.
26. localStorage: save/load scores and settings.
27. Mobile touch testing; adjust column tap-target sizes.
28. Full column visual feedback (dim drop button when column full).

**Phase 5 — Accessibility + QA (Day 3)**
29. ARIA labels on board cells.
30. Keyboard navigation (tab + Enter/arrow keys).
31. Colorblind differentiation (label or pattern).
32. Cross-browser test (Chrome, Firefox, Safari, Edge, mobile).
33. No-console-errors pass.
34. Confirm file ends `</html>` and is fully self-contained.

### Recommended Tech Stack
- HTML5 + CSS3 + Vanilla ES6 JS
- No external libraries (no React, no jQuery, no game engine)
- WebAudio API for SFX
- localStorage for persistence

### Required Asset List
- No image files (CSS-only disc rendering with border-radius: 50%)
- No audio files (WebAudio generated tones)
- No font files (system font stack: `-apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif`)

### Hardest Parts / Risks
1. **Minimax performance at depth 7**: must stay under ~500 ms; alpha-beta pruning + center-move-ordering is essential. Risk: deep early-game search. Mitigation: cap depth by game phase (lower depth when board is sparse, increase as board fills). [Estimated]
2. **Diagonal win detection**: both diagonal directions (↗ and ↘) must be checked correctly; off-by-one errors are the most common bug. Test with diagonals explicitly.
3. **Drop animation + input lock**: must disable player input during animation AND AI thinking; race conditions possible if not handled with a simple `isAnimating` flag.
4. **Mobile touch targets**: 7 columns on a 320px phone = ~45px per column, acceptable but tight. Drop-buttons row above board is the safety net.
5. **Gravity correctness**: always place in the **lowest** empty row (highest row index in a 0=top system, or lowest index in a 0=bottom system). Define convention clearly to avoid inverted-board bugs.

---

## 17. Open Questions

1. **Optimal Hard AI depth by phase**: Depth 7 is estimated adequate for Hard AI. Playtesting may reveal depth 6 is fast enough and depth 8 is perceptibly slow on older mobile hardware. Tune via config constant. [Needs playtesting]
2. **Exact Hasbro color trade dress**: Hasbro's exact blue tone (#003087 PMS 072) is a trade dress consideration. The clone uses a clearly distinct blue (#1a3a6b) to avoid confusion. [Needs legal review if commercializing]
3. **Online multiplayer**: Not in scope for this clone. If added later, a WebSocket-based turn relay (no server-side game logic required) is the minimal approach.

---

*Blueprint written for an original-branded clone. Mechanics are in the public domain; do not copy Hasbro/Milton Bradley trade dress, logos, or copyrighted UI assets.*
