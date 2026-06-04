# Dots and Boxes — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Dots and Boxes is a classic pencil-and-paper combinatorial strategy game for 2 players (human vs human or human vs AI). Players take turns drawing a single line between two adjacent dots on a grid; completing the fourth side of a 1×1 square claims that box and awards an extra turn. The player with the most boxes when the grid is full wins. It is deceptively simple to learn and deep to master thanks to chain theory. [Confirmed — Wikipedia / UCLA Math / quietpuzzle.com]

**Quick facts:**
- Origin: Édouard Lucas, 1889 ("La Pipopipette") [Confirmed]
- Platforms targeted for clone: Web (HTML/JS), Mobile (PWA), optional native wrapper
- Typical session: 2–10 minutes depending on grid size
- Play style: Turn-based, active, two-player competitive
- Age/content rating: Suitable for all ages (IARC 3+); no violence, no data collection required for core play [Estimated]
- Monetization (clone recommendation): None for core; optional "Remove Ads" IAP if ads added

---

## 2. Core Loops

- **30-second loop:** Select an undrawn edge between two adjacent dots → line appears → if a box is completed, claim it and take another turn → otherwise opponent's turn.
- **Session loop:** Choose grid size and mode → play through all edges until grid is full → see final score and winner → optionally replay.
- **Meta loop:** Beat increasingly hard AI difficulty levels; try larger grids for longer challenge; track win/loss record across sessions via localStorage leaderboard.

---

## 3. Mechanics, Controls & Game States

### Core Rules [Confirmed]
1. Board is an N×M grid of dots (e.g. 4×4 dots = 3×3 boxes).
2. Players alternate placing a single horizontal or vertical line segment connecting two orthogonally adjacent dots that are not yet connected.
3. If placing a line completes the **fourth side** of one or more 1×1 boxes, those boxes are claimed (filled with the active player's color) and the active player **takes another turn immediately**.
4. A player may complete multiple boxes in one move (e.g. drawing the shared wall between two almost-complete boxes) — all are claimed and the extra turn rule still applies.
5. When every possible line has been drawn, the game ends. The player with the most claimed boxes wins. Ties are possible.

### Core Verbs
- **Draw line** — tap/click an edge between two adjacent dots.
- **Claim box** — automatic on fourth-side completion.
- **Pass turn** — automatic when no box is completed.

### Game Modes
| Mode | Description |
|---|---|
| vs AI (Easy) | Human vs random-safe AI (avoids giving third side) |
| vs AI (Medium) | Human vs AI using greedy chain-grabbing |
| vs AI (Hard) | Human vs AI using chain theory + double-cross heuristic |
| 2-Player Local | Human vs Human on same device, alternating turns |

### Grid Sizes [Estimated based on convention]
| Label | Dots Grid | Boxes |
|---|---|---|
| Small (3×3) | 4×4 dots | 9 boxes |
| Medium (5×5) | 6×6 dots | 25 boxes |
| Large (7×7) | 8×8 dots | 49 boxes |

### Input Scheme
- **Desktop:** Mouse hover to highlight edge; click to draw.
- **Mobile:** Tap edge (large invisible hit areas around each potential edge, minimum 44px touch target). No drag required.
- Orientation: Portrait preferred on mobile; landscape also supported via flexible layout.

### Win / Lose / Fail Conditions
- **Win:** More boxes claimed than opponent at game end.
- **Lose:** Fewer boxes claimed.
- **Tie:** Equal boxes (stated explicitly).
- **No fail state / no lives** — game simply ends when board is full.
- On game end: banner overlay with result + scores + "Play Again" button.

### AI Behavior [Confirmed + Estimated]

**Easy AI:**
- Picks a random valid edge.
- One safety check: avoids drawing the third side of any box unless no safe move exists (forces board depletion).

**Medium AI:**
1. First priority: complete any box available (grab free boxes).
2. Second priority: avoid giving opponent a third side.
3. Fallback: random safe move.

**Hard AI (Chain Theory):** [Confirmed — based on academic papers]
1. Complete all available boxes (greedy capture).
2. Evaluate remaining moves — avoid "3-sided" boxes (giving opponent a chain opener).
3. Apply **double-cross** tactic: when forced to open a chain, sacrifice 2 boxes to pass control.
4. Count chain parity: prefers moves that leave opponent to open the first long chain.
5. Uses depth-limited lookahead (2–4 ply) for smaller grids; heuristic evaluation for larger grids.
6. Never plays an illegal move (already-drawn edges disabled).

### Feedback Systems
- Hover: edge glows/highlights on mouseover.
- Click: line animates in (stroke grows from midpoint).
- Box claim: box fills with player color + subtle bounce/pulse animation.
- Scoring: score counters animate up.
- Extra turn: brief "Go again!" text pulse near active player indicator.
- Game end: confetti or color flash + winner banner.
- Audio: line-draw click, box-claim chime, win fanfare, extra-turn ding.

---

## 4. Progression

### In-Game Progression
- No XP, levels, or unlock trees — the game is a single self-contained match.
- **Skill progression is implicit**: mastering chain theory is the depth.

### Cross-Session Progression (Clone)
- localStorage scoreboard: wins / losses / ties per mode and grid size.
- "Best streak" counter per difficulty.
- Difficulty levels act as a progression ladder: beat Easy → Medium → Hard.

### Gating
- No paywalls, energy systems, or time gates for core gameplay.
- All grid sizes and modes unlocked from the start [Estimated — appropriate for a board game clone].

---

## 5. Economy & RNG

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| None | — | — | — |

No economy. No currencies, upgrades, or purchasable items in the core game. [Confirmed]

### RNG
- Easy AI uses random edge selection (random but with safety filter).
- Medium/Hard AI uses deterministic heuristics — no RNG beyond Easy.
- No loot, no drops, no gacha.

### Cost Scaling
N/A — no economy.

---

## 6. Content Inventory

| Category | Count / Details |
|---|---|
| Grid sizes | 3 (Small 3×3, Medium 5×5, Large 7×7 boxes) |
| Game modes | 4 (Easy AI, Medium AI, Hard AI, 2-Player Local) |
| Player colors | 2 (Player 1 and Player 2 / AI, distinct high-contrast colors) |
| Difficulty levels | 3 (Easy, Medium, Hard) |
| Sound effects | ~5 (draw, claim, extra-turn, win, tie) |
| UI screens | 4 (Menu, Game Board, End Screen/Banner, Settings) |

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract geometric — dots on a neutral background.
- **Premise:** No story. Pure combinatorial game. [Confirmed]
- **Visual theme:** Clean, minimal, modern flat design. Grid dots are circles; lines are colored strokes; claimed boxes are semi-transparent color fills.
- **Tone:** Playful-competitive. Satisfying to play; light sound/animation feedback gives it "juice" without being overwrought.
- **Color palette (clone suggestion):** Neutral dark/light background; Player 1 = vivid blue (#2979FF); Player 2/AI = vivid orange/red (#FF6D00); unclaimed edges = subtle gray; hover = bright white/yellow.
- **No licensed IP.** Name the clone something original (e.g. "Line & Claim", "Grid Duel", "Square Up").

---

## 8. Meta & Social Systems

### In the Original (pencil-and-paper)
- No meta systems; purely a single match.

### Clone Additions [Estimated]
- **localStorage scoreboard:** win/loss/tie tally per mode; shown on menu screen.
- **Settings persistence:** last-used grid size, mode, difficulty, mute state saved to localStorage.
- **No multiplayer server, no accounts, no leaderboards, no daily rewards** — appropriate for a self-contained web game.
- **Live-ops cadence:** None required. Game is evergreen.
- **Social:** Optional share-result button (generates text string for copy-paste).

---

## 9. UI/UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Main Menu | Mode select, grid size, difficulty, start game, view scoreboard, settings |
| Game Board | Active gameplay — grid, scores, turn indicator, mute, new game |
| End Banner (overlay) | Show winner + final scores; Play Again / Main Menu buttons |
| Settings Modal | Mute toggle, how-to-play text, reset scoreboard |

### Settings / Options Menu Contents
- Sound on/off (mute toggle)
- How to Play (inline instructions)
- Reset scoreboard
- No account, no language selector (single-language clone)

### Gameplay HUD Elements
- Grid (central, dominant)
- Player 1 name + score (top or side)
- Player 2 / AI name + score (top or side, opposite)
- Turn indicator ("Your turn" / "AI thinking...")
- Difficulty badge (when vs AI)
- Mute button (corner)
- New Game button (corner)

### Navigation Flow
```
Main Menu → [Start Game] → Game Board → [Game Over] → End Banner → [Play Again] → Game Board (reset)
                                                                   → [Main Menu] → Main Menu
Main Menu → [Settings] → Settings Modal → [Close] → Main Menu
```

### Onboarding / Tutorial Steps (First-Time Players)
1. On first load: brief tooltip overlay on the grid: "Tap any edge between two dots to draw a line."
2. After first line drawn: "Complete 4 sides of a box to claim it and go again!"
3. After first box claimed: "You get another turn when you complete a box. Most boxes wins!"
4. Tutorial auto-dismisses; not shown on subsequent visits (localStorage flag).

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D** — flat top-down view of grid. No camera movement. [Confirmed]
- Display: portrait-first responsive layout; desktop centers grid with side panels.

### Art Style
- Flat vector / minimal. Dots = filled circles (~8–12px radius). Lines = colored rectangles/strokes (~4–6px thick). Claimed boxes = semi-transparent fills (20–30% opacity overlay of player color).
- Grid dot color: neutral mid-gray or white outline on dark bg.
- Hover edge: light glow or yellow highlight.
- Drawn edge: solid player color.
- Font: clean sans-serif (system stack: -apple-system, Segoe UI, sans-serif).

### Animation
- Line draw: CSS/JS stroke animation 80–120ms.
- Box claim: fill opacity fades in over 200ms + slight scale pulse.
- Turn transition: subtle fade/slide on score numbers.
- Extra turn badge: brief text pop.
- Win: confetti burst or color flash overlay.

### VFX
- Particle pop on box completion (optional, lightweight canvas particles).
- Screen flash on win.

### Audio (WebAudio API, generated — no files)
| Sound | Trigger | Character |
|---|---|---|
| Line draw | Any valid edge placed | Soft click/snap |
| Box claimed | Box completed | Higher-pitched chime |
| Extra turn | Extra turn granted | Quick ascending ding |
| Win | Game ends, you won | Short fanfare / ascending chord |
| Tie / Lose | Game ends, tie or loss | Neutral / descending tone |

- Mute toggle persists to localStorage.

---

## 11. Monetization

### Core Game
- **No monetization** in the base clone. Fully free, no ads, no IAP required.

### Optional Monetization (if clone is published as a web/app game) [Estimated]
| Type | Placement | Notes |
|---|---|---|
| Rewarded video ad | "Watch ad to undo last move" | Once per game max |
| Banner ad | Below game board on mobile | Low-intrusion |
| Interstitial | Between games (not during) | Max 1 per 3 games |
| IAP: Remove Ads | $1.99 one-time | Removes all ad placements |

### Loot / Gacha
- None. [Confirmed — classic board game, no RNG economy]

### Consent / ATT / CMP
- If ads added: implement IAB TCF CMP banner on first load (GDPR compliance for EU users).
- iOS: ATT prompt before showing any personalized ads (Apple requirement).
- COPPA: if targeting under-13, disable behavioral ads entirely and do not collect device IDs.
- For core no-ad clone: no consent flows required.

### Aggressiveness
- Core clone: zero monetization pressure. Appropriate for a classic game clone.

---

## 12. Retention Hooks

### In Core Game
- No retention hooks by design (classic board game). [Confirmed]

### Clone Additions [Estimated]
- **Persistent scoreboard** (localStorage): win/loss tally incentivizes replaying.
- **Difficulty ladder**: beating Hard AI is a natural achievement goal.
- **Grid size variety**: 3 distinct sizes provide replay variety.
- **No energy system, no lives, no daily reward** — always playable, no artificial gates.
- **No push notifications** in web version (appropriate for a board game clone).

### Idle / Offline Earnings
- None. Not applicable to this game type.

---

## 13. Localization & Accessibility

### Localization [Estimated]
- Clone scope: English only (single language).
- All strings in a constants object for easy translation.
- No RTL support needed for English; architecture should support it if expanded.
- No regional pricing differences (no IAP in base clone).

### Accessibility
- **Colorblind mode:** Use distinct shapes (dot fill / pattern) in addition to color for claimed boxes. Ensure Player 1 / Player 2 colors are not red-green (use blue/orange). [Estimated]
- **Text scaling:** UI text uses relative units (rem/em) — scales with browser font size settings.
- **Touch targets:** All interactive edges have minimum 44×44px invisible hit areas on mobile. [Confirmed — WCAG 2.5.5]
- **Keyboard navigation:** Tab to cycle edges, Enter/Space to draw (optional enhancement).
- **Reduced motion:** Respect `prefers-reduced-motion` media query — skip animations.
- **Screen reader:** ARIA live region for turn announcements and score updates.

### Age / Content Rating
- IARC 3+ / ESRB Everyone. No violence, no user data collection in base clone.
- COPPA compliant by default (no PII collection, no behavioral ads).

---

## 14. Technical Structure

### Engine / Framework
- **Vanilla HTML5 / CSS3 / JavaScript** — no framework, no build step. [Confirmed — per build spec]
- Rendering: Canvas API (preferred for smooth hit-testing) or DOM/SVG.
- Single self-contained `.html` file with inline `<style>` and `<script>`.

### Platforms
- Any modern browser (Chrome, Firefox, Safari, Edge). Desktop + Mobile.
- Offline capable — no network requests. Runs by double-click.
- PWA wrapper optional (add manifest.json + service worker for installability).

### Save System
- `localStorage` for: scoreboard (wins/losses/ties per mode+size), settings (mute, last mode, last size, last difficulty), tutorial-seen flag.
- No accounts, no cloud sync, no login.

### Multiplayer
- Local 2-player only (pass-and-play on one device). No networked multiplayer in base clone.

### Anti-Cheat / Server Authority
- N/A — local game, no server, no online play.

### Backend Services
- None. Zero backend required.

### Third-Party SDKs
- None in base clone.

### Analytics
- None in base clone. If added: privacy-first (e.g. Plausible), no fingerprinting.

### App Size
- Estimated ~30–80KB for complete .html (no images, generated audio) [Estimated].

### Performance Notes
- Canvas or DOM grid redraws are trivial at ≤8×8 dot grids.
- AI hard difficulty: cap lookahead to 4 ply on 7×7 to keep move time <300ms.

---

## 15. Pacing & Difficulty Curve

### Early Game (first ~30% of edges)
- Both players draw "safe" edges (sides that don't give a third side to any box).
- Tension builds as available safe moves diminish.
- New players: may accidentally give away boxes early.

### Mid Game (30–70% of edges)
- Critical phase: boxes start to have 2 sides. Every move risks giving the opponent a chain.
- The "sacrifice" tactic becomes relevant — intentionally give a short chain to keep control.
- Medium AI starts to shine here; hard AI begins chain counting.

### Late Game (70–100% of edges)
- Long chains dominate. The player forced to open the first long chain usually loses.
- Hard AI applies double-cross: takes all but 2 in a chain, forces opponent into next chain.
- Expert human play: chain parity control.

### Key Milestone ("Aha") Moments
1. First time you claim a box and realize you get another turn — delight.
2. First time you claim a 4-box chain in one turn — excitement.
3. First time an opponent steals your almost-complete chain — frustration → learning.
4. Beating Hard AI for the first time — significant achievement.

### Churn Points [Estimated]
- Players may quit if Easy AI feels too hard (misconfigured). Ensure Easy truly avoids third-side gifts.
- Very large grids (7×7, 49 boxes) may feel long for casual players — keep as an option.
- Loss to AI with no explanation: add brief end-screen tip ("Tip: avoid drawing the 3rd side of a box").

### Difficulty Curve
```
Easy AI    → Random moves with basic safety; loses often even to new players
Medium AI  → Greedy + defensive; competitive against casual players
Hard AI    → Chain theory; will beat most humans; requires real strategy to defeat
2-Player   → Depends entirely on both players' skill levels
```

---

## 16. Clone Build Plan

### MVP Feature Set vs Full Feature Set

| Feature | MVP | Full |
|---|---|---|
| Core grid gameplay (draw edges) | ✓ | ✓ |
| Box completion + extra turn | ✓ | ✓ |
| 2-player local | ✓ | ✓ |
| Easy AI | ✓ | ✓ |
| Medium AI | ✓ | ✓ |
| Hard AI (chain logic) | ✓ | ✓ |
| 3 grid sizes | ✓ | ✓ |
| Score display + turn indicator | ✓ | ✓ |
| Win/end detection + banner | ✓ | ✓ |
| New game / restart | ✓ | ✓ |
| Mobile touch (44px hit areas) | ✓ | ✓ |
| Responsive layout | ✓ | ✓ |
| localStorage scoreboard | ✓ | ✓ |
| Settings persistence | ✓ | ✓ |
| Sound effects (WebAudio) | ✓ | ✓ |
| Mute toggle | ✓ | ✓ |
| How-to-play | ✓ | ✓ |
| Onboarding tooltip | — | ✓ |
| Colorblind mode | — | ✓ |
| Keyboard navigation | — | ✓ |
| AI move animation delay | — | ✓ |
| Undo move (rewarded) | — | ✓ |
| Custom player names | — | ✓ |
| Share result | — | ✓ |

### Phased Build Roadmap

**Phase 1 — Core Engine (Days 1–2)**
1. HTML skeleton: meta viewport, canvas or DOM container.
2. Grid data model: dots array, horizontal edges array, vertical edges array, boxes array.
3. Edge state: `{drawn: bool, player: null|1|2}`.
4. Box state: `{claimed: bool, player: null|1|2, sides: count}`.
5. Edge placement logic: mark edge drawn → check all adjacent boxes for 4-sided completion → claim + extra-turn or pass turn.
6. Render loop: draw dots, draw edges (state-colored), draw box fills.
7. Click/tap handler: map pixel coords → nearest edge → place if valid.

**Phase 2 — UI Shell (Day 2–3)**
8. Mode selector, difficulty selector, grid size selector.
9. Score display (P1 vs P2), turn indicator, mute button, new game button.
10. End game banner overlay.
11. CSS responsive layout (flexbox, viewport units, media queries).

**Phase 3 — AI (Day 3–4)**
12. Easy AI: random valid edge, filtered by "no third-side gift" (if possible).
13. Medium AI: priority queue — grab completable boxes > avoid third-side > random.
14. Hard AI: chain analysis — enumerate chains, apply double-cross heuristic, depth search.
15. AI delay timer (300–600ms) for UX realism.

**Phase 4 — Polish (Day 4–5)**
16. WebAudio SFX generation (oscillator-based clicks, chimes).
17. CSS/JS animations for line draw and box fill.
18. Hover/active edge highlighting (canvas redraw on mousemove).
19. Mobile hit-area padding in click handler.
20. localStorage scoreboard + settings persistence.
21. How-to-play text in settings modal.
22. Onboarding tooltip (first visit).

**Phase 5 — QA (Day 5)**
23. Test all grid sizes × all modes × all difficulties.
24. Verify edge uniqueness (no double-draw).
25. Verify extra-turn rule (1 box, 2 boxes, chain of N boxes — all keep turn).
26. Verify turn passing only on zero-box moves.
27. Verify end condition triggers correctly (all edges drawn).
28. Verify winner by box count (including tie).
29. Verify AI never plays a taken edge.
30. Touch testing on mobile viewport.
31. localStorage read/write cycles.
32. Console error check.

### Recommended Tech Stack (for clone)
- HTML5 + CSS3 + Vanilla JavaScript (ES6+)
- Canvas API for grid rendering (better hit-testing precision than DOM)
- WebAudio API for generated SFX
- localStorage for persistence
- No frameworks, no dependencies — runs offline, zero build step

### Required Asset List
| Asset | Type | Notes |
|---|---|---|
| Grid renderer | Code | Canvas draw calls |
| Edge hit-testing | Code | Map pixel → edge index |
| Box completion logic | Code | Check 4 adjacent edges |
| AI modules (3 levels) | Code | Easy/Medium/Hard classes |
| WebAudio SFX generator | Code | 5 sounds via oscillator |
| CSS theme | Code | Player colors, hover states |
| Scoreboard UI | Code | localStorage + DOM |

No external art or audio files required.

### Hardest Parts / Biggest Risks
1. **Hard AI correctness** — chain parity logic is non-trivial. Risk: AI makes bad sacrifices on larger grids. Mitigation: test extensively on 5×5 and 7×7; cap lookahead depth to prevent hangs.
2. **Touch hit areas** — invisible hit zones for edges must not overlap each other. Risk: wrong edge selected on mobile. Mitigation: use center-point distance calculation to nearest edge, with minimum 22px radius.
3. **Edge coordinate mapping** — consistent mapping between pixel coords and grid edge index is easy to get off-by-one. Mitigation: clear data model with explicit index formulas.
4. **Extra-turn chain (multiple boxes per move)** — must handle the case where one drawn line completes 2 boxes simultaneously (shared wall). Mitigation: check all adjacent boxes after every draw, not just the first.
5. **Grid size hot-switching** — changing grid size must fully reset state without artifacts. Mitigation: encapsulate all state in a `newGame()` function.

---

## 17. Open Questions

1. **Hard AI depth on 7×7 grids**: Lookahead past 4 ply may be slow in pure JS. May need to reduce to pure heuristic (no tree search) on large grids — needs timing tests. [Estimated threshold: switch to heuristic-only at 7×7]
2. **Double-cross precision**: The academic double-cross strategy is fully optimal for end-game chains but complex to implement perfectly. Initial implementation uses an approximation; a future pass could implement full chain analysis. [Estimated — approximation is good enough for "Hard" casual AI]
3. **Tie-breaking display**: If exact tie, show "It's a tie!" vs "You won" vs "AI wins" — copy needed from designer.
4. **Animation on very slow devices**: Canvas animation on low-end Android may stutter at 60fps. May need to reduce to instant draw on `matchMedia('(prefers-reduced-motion)')` or a performance flag.

---

*Blueprint version 1.0 — June 2026. Build-ready for a self-contained web implementation.*

Sources consulted:
- [Some Strategy For Dots and Boxes — UCLA Math](https://www.math.ucla.edu/~tom/Games/dots&boxes_hints.html)
- [Dots and Boxes — Wikipedia](https://en.wikipedia.org/wiki/Dots_and_boxes)
- [Dots and Boxes Rules, Variants, Chain Strategy](https://quietpuzzle.com/en/dots_and_boxes/)
- [Solving Dots-And-Boxes — Barker & Korf, AAAI](https://cdn.aaai.org/ojs/8144/8144-13-11671-1-2-20201228.pdf)
- [Application of Minimax Algorithm in Dots and Boxes](http://eprints.utar.edu.my/6132/1/fyp_IA_2025_TAH.pdf)
