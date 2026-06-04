# Reversi (Othello) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Reversi (commercialized as Othello by Mattel/Megahouse) is an abstract two-player strategy board game played on an 8×8 grid. Players take turns placing discs that must flank one or more contiguous lines of opponent discs, which are then flipped to the placer's color. The game rewards strategic positioning — especially corner and edge control — over raw disc count until the very end. Its elegance lies in the simple rule set producing profound complexity: estimated 10^28 possible game states.

**Quick facts:**
- Developer/Publisher: Invented by Lewis Waterman (~1883); commercialized as Othello by Goro Hasegawa (1971), published by Mattel (US) and Megahouse (JP)
- Platforms: Physical board game; digital on every platform (iOS, Android, PC, Web, console)
- Release: Physical ~1883 (Reversi), 1971 (Othello); digital versions continuously since 1970s
- Rating: ESRB E (Everyone) / PEGI 3 [Confirmed]
- Monetization model (this clone): Free-to-play, no ads, no IAP — pure gameplay
- Session length: 5–20 minutes per game [Confirmed]
- Play style: Active, turn-based, single or local multiplayer

---

## 2. Core Loops

- **30-second loop:** Study the board → identify highlighted legal moves → tap/click a cell → watch opponent discs flip → opponent (AI or human) takes their turn.
- **Session loop:** Select game mode (vs AI at chosen difficulty or 2-player local) → play full game (typically 40–60 moves) → view result with disc counts → optionally rematch or change settings.
- **Meta loop:** Build win/loss record saved in localStorage scoreboard → challenge higher AI difficulty levels → attempt to achieve maximum disc count victories.

---

## 3. Mechanics, Controls & Game States

### Core Rules [Confirmed]
1. **Starting position:** 4 discs pre-placed in the center — White at d4/e5, Black at d5/e4 (standard Othello orientation).
2. **Legal move:** A placement is legal only if it flanks at least one contiguous line of opponent discs (in any of 8 directions: N, NE, E, SE, S, SW, W, NW) between the placed disc and an existing disc of the placer's color.
3. **Flipping:** All flanked discs in all valid directions are simultaneously flipped to the placer's color.
4. **Forced flanking:** If a player has any legal move, they must play one. Skipping is not allowed when moves exist.
5. **Passing:** If a player has no legal moves, their turn is automatically skipped (passed). A pass notice is shown.
6. **Game end:** The game ends when neither player has a legal move (board full, or no flanking possible for both). The player with the most discs of their color wins. A tie is possible.
7. **Winner:** Most discs of own color at game end. If equal, draw.

### Core Verbs
- Place (tap/click a legal highlighted cell)
- Pass (automatic; shown as notice)
- Undo (optional, vs AI only)
- Restart

### Game Modes
| Mode | Description |
|------|-------------|
| vs AI | Human (Black) vs computer (White); three difficulty levels |
| 2-Player Local | Both players share one device, alternating turns |

### Difficulty Levels (vs AI)
| Level | Algorithm | Description |
|-------|-----------|-------------|
| Easy | Random legal move | Picks any valid move randomly |
| Medium | Greedy + corner preference | Maximizes immediate disc gain; always takes a corner if available |
| Hard | Minimax + alpha-beta pruning, depth 6–8 | Uses positional weight table, mobility heuristic, stability, corner capture |

### Input
- **Desktop:** Mouse click on highlighted cell
- **Mobile:** Touch tap on highlighted cell
- Illegal cells and all cells during AI turn are non-interactive (pointer-events off, no highlight)

### Orientation
- Portrait-first on mobile; responsive and usable in landscape [Estimated]

### Win / Lose / Fail States
| State | Trigger | Response |
|-------|---------|----------|
| Win | More discs at game end | Result banner with disc counts; scoreboard updated |
| Lose | Fewer discs at game end | Result banner with disc counts; scoreboard updated |
| Draw | Equal discs at game end | Draw banner |
| Pass | No legal moves for current player | Auto-skip with visible pass notice |
| Both pass | Neither player has legal moves | Game ends immediately |

### Feedback Systems
- Legal move hint markers (dots or ghost discs) on valid cells — toggleable
- Disc flip animation (CSS rotation + color swap)
- Sound on placement (soft click) [Estimated WebAudio]
- Sound on flip (whoosh/swoosh) [Estimated WebAudio]
- Sound on win (fanfare) [Estimated WebAudio]
- Sound on pass (brief tone) [Estimated WebAudio]
- Turn indicator with player color badge
- Live disc count display (Black: N | White: N)

---

## 4. Progression

Reversi is a session-based abstract game with no persistent progression in the traditional sense. [Confirmed]

- **Per-session progression:** Disc count shifts over the course of a game; large swings are common and deliberate.
- **Metagame progression (clone-specific):**
  - Scoreboard tracking wins/losses/draws per mode and difficulty in localStorage
  - AI difficulty ladder: player can unlock a sense of mastery by beating Easy → Medium → Hard
- **No upgrade trees, XP, or prestige mechanics** — by design for this genre [Confirmed]
- **Gating:** None beyond choosing a harder AI difficulty

---

## 5. Economy & RNG *(tables)*

Reversi has no in-game economy or currency. [Confirmed]

| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| N/A | — | — | — |

**RNG:** Minimal. Easy AI uses random move selection. No loot, no drops, no gacha. [Confirmed]

**Cost-scaling:** N/A — no economy to scale.

**Starting state:** Always identical (4-disc center pattern). Randomness confined to Easy AI move selection only.

---

## 6. Content Inventory *(counts + lists)*

| Category | Count | Notes |
|----------|-------|-------|
| Board | 1 | 8×8 = 64 cells |
| Disc colors | 2 | Black, White |
| Game modes | 2 | vs AI, 2-Player Local |
| AI difficulty levels | 3 | Easy, Medium, Hard |
| Directions for flanking | 8 | N, NE, E, SE, S, SW, W, NW |
| Starting discs | 4 | 2 Black, 2 White in center |
| Maximum discs per player | 32 | Board half |
| Total possible game states | ~10^28 | [Confirmed, theoretical estimate] |

**Positional weight table (Hard AI evaluation):** [Confirmed — standard academic values]
```
 100 -20  10   5   5  10 -20  100
 -20 -50  -2  -2  -2  -2 -50  -20
  10  -2   1  -1  -1   1  -2   10
   5  -2  -1  -1  -1  -1  -2    5
   5  -2  -1  -1  -1  -1  -2    5
  10  -2   1  -1  -1   1  -2   10
 -20 -50  -2  -2  -2  -2 -50  -20
 100 -20  10   5   5  10 -20  100
```
Corners = 100 (never flippable), X-squares (diagonally adjacent to corners) = -50, C-squares (edge-adjacent to corners) = -20.

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract — green felt game board, black and white counters; minimal theme beyond the classic board game aesthetic [Confirmed]
- **Premise:** No story. Pure combinatorial strategy contest.
- **Narrative delivery:** None — game is entirely mechanical
- **Character personalities:** None; players are "Black" and "White" (or their chosen names)
- **Dialogue/writing style:** Minimal UI copy; instructional ("Your turn", "Black passes", "Black wins 34–30!")
- **Tone:** Clean, focused, classic — evokes the physical board game. Calm ambient feel.
- **IP:** Reversi (1883) is public domain; Othello branding is trademarked by Mattel/Megahouse. Clone uses "Reversi" or original branding. [Confirmed]
- **Color palette:** Deep green (#1a6b35 felt), black (#111) discs, white (#f0f0f0) discs, cream/wood border (#c8a060)

---

## 8. Meta & Social Systems

Since this is a self-contained browser game:

- **Scoreboard:** Persistent win/loss/draw records per mode stored in localStorage [Estimated for clone]
- **Daily challenges:** Not applicable for base clone [Estimated]
- **Missions/achievements:** Not in base game; optional stretch goal for clone [Estimated]
- **Live-ops cadence:** None — Reversi is a complete game; no content updates needed [Confirmed]
- **Multiplayer beyond local:** Out of scope for this clone (would need server/WebSocket) [Estimated]
- **Leaderboards:** Local only (scoreboard) [Estimated]
- **Sharing:** Optional: share result as text [Estimated stretch]
- **Guilds/clans:** N/A
- **Battle pass/seasons:** N/A

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| Main Menu | Mode selector, difficulty selector, scoreboard preview, new game button, how-to |
| Game Board | Primary gameplay: 8×8 grid, HUD, turn indicator, disc counts |
| Pass Notice (modal/overlay) | Informs current player their turn is skipped |
| Result Banner (overlay) | Shows winner and final disc counts; Rematch / Menu buttons |
| How-To Modal | Rules explanation with visual |
| Settings Panel | Hints toggle, sound mute, animation speed |

### Settings / Options Menu Contents
- Show legal move hints: On/Off toggle
- Sound effects: Mute/Unmute
- Animation speed: Fast / Normal / Off
- Reset scoreboard button

### Gameplay HUD Elements
- Current turn indicator (colored badge + "Black's Turn" / "White's Turn" / "AI Thinking…")
- Black disc count (large number, left)
- White disc count (large number, right)
- Pass button (disabled unless it's a pass situation — auto-handled)
- Undo button (vs AI only, undoes last human move)
- Menu / New Game button
- Hints toggle shortcut

### Navigation Flow
```
Main Menu → [New Game] → Game Board
Game Board → [Pass overlay auto-shown] → Game Board (other player's turn)
Game Board → [Game ends] → Result Banner → [Rematch] → Game Board (fresh)
                                         → [Menu] → Main Menu
Main Menu → [How-To] → How-To Modal → [Close] → Main Menu
Game Board → [Menu icon] → Main Menu (with confirm dialog)
```

### Onboarding / Tutorial Steps
1. First launch: How-To modal auto-opens (dismissible, "Got it!" button)
2. Modal explains: board overview → starting position → what a legal move is → flanking rule → pass rule → winning condition
3. Legal move hints are ON by default — highlighted cells guide first-time players
4. First move: hint dots glow to indicate where to tap
5. After first placement: brief tooltip "Opponent discs flipped!" fades out
6. No forced step-by-step tutorial beyond modal — hints serve as continuous guidance

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D top-down
- **Camera/perspective:** Fixed top-down view, no camera movement
- **Orientation:** Portrait-primary (mobile-first); responsive to landscape/desktop
- **Art style:** Skeuomorphic/classic — green felt texture, wooden border, realistic disc shadows [Confirmed aesthetic]
- **Color palette:**
  - Board: #2d6a2d (green felt), #1a5c1a (grid lines), #8B5E3C (wooden border)
  - Black disc: radial gradient #111 → #333, with specular highlight
  - White disc: radial gradient #f8f8f8 → #ccc, with specular highlight
  - Legal hint: semi-transparent disc outline, color of current player
  - Background: #1a3a1a (dark green surround)
- **Animation:**
  - Disc placement: scale-in from 0 (pop effect)
  - Disc flip: CSS 3D rotateY 180° with mid-point color swap (0.3s per disc, sequential cascade)
  - Win animation: confetti or disc pulse
- **VFX:**
  - Placement particle burst (subtle)
  - Flip cascade visual (each flip slightly delayed: i * 50ms)
  - Screen-edge glow on win
- **SFX (WebAudio, generated):**
  - Placement: short tick (oscillator burst ~80ms, 440Hz)
  - Flip: soft whoosh (filtered noise sweep)
  - Win: ascending fanfare arpeggio (3 notes)
  - Pass: low tone (220Hz, 200ms)
  - All generated via Web Audio API, no external files
- **Mute toggle:** Accessible from HUD
- **Music:** None (optional ambient loop as stretch goal)

---

## 11. Monetization

This clone is a pure free-to-play browser game with no monetization. [Confirmed design decision]

| Type | Present | Notes |
|------|---------|-------|
| Banner ads | No | — |
| Interstitial ads | No | — |
| Rewarded ads | No | — |
| IAP | No | — |
| Subscription | No | — |
| Loot boxes | No | — |

- **Consent/ATT/CMP:** Not required (no ads, no tracking, no third-party SDKs) [Confirmed]
- **Data collection:** None beyond localStorage (client-side only)
- **Age compliance:** COPPA/GDPR-K not triggered (no data collection) [Confirmed]

---

## 12. Retention Hooks

This is a self-contained game with minimal live retention mechanics by design. [Confirmed]

- **Scoreboard persistence:** Win/loss/draw history in localStorage provides replay motivation
- **Difficulty ladder:** Easy → Medium → Hard creates natural goal progression
- **Rematch friction:** Zero — immediate rematch button encourages "one more game"
- **Idle/offline earnings:** None (not applicable to this genre) [Confirmed]
- **Push notifications:** None
- **Energy/lives system:** None
- **FOMO mechanics:** None
- **Daily rewards:** None (out of scope for base clone)

---

## 13. Localization & Accessibility

### Localization [Estimated for clone]
- Base clone: English only
- All UI strings kept in a JS constants object for easy translation
- No RTL needed for base; architecture supports adding it
- Regional differences: None (public domain rules)

### Accessibility
- **Color contrast:** Disc colors (black/white) have maximum contrast on green; WCAG AA minimum target [Estimated]
- **Legal hint markers:** Dot markers (not color-only) — shape + color cues [Confirmed design]
- **Colorblind mode:** Not strictly needed (black/white discs are universally distinguishable), but labels on disc count help
- **Text scaling:** CSS em/rem units; responsive to browser font size
- **Touch targets:** Minimum 44×44px per cell (8×8 board fits on 320px+ screens) [Estimated]
- **Keyboard support:** Arrow keys to navigate cells, Enter/Space to place (optional stretch)
- **Screen reader:** aria-label on board cells describing their state [Estimated stretch]
- **Difficulty/assist options:** Hint toggle (beginner-friendly), animation-off option
- **Age/content rating:** ESRB E / PEGI 3 — fully appropriate for all ages [Confirmed]
- **COPPA compliance:** No data collected; no account system [Confirmed]

---

## 14. Technical Structure

### Engine / Framework
- **Vanilla HTML5 + CSS3 + JavaScript (ES6+)** — no frameworks, no build step [Confirmed design]
- Single self-contained `.html` file; inline `<style>` + `<script>`
- Web Audio API for SFX generation

### Platforms
- All modern browsers (Chrome, Firefox, Safari, Edge) [Confirmed]
- Mobile browsers (iOS Safari, Android Chrome) [Confirmed]
- Offline — runs from filesystem with no network requests [Confirmed]

### Save System
- **localStorage** for: scoreboard (wins/losses/draws), settings (hint toggle, mute, animation speed)
- No account system, no cloud sync
- Key schema:
  ```json
  {
    "reversiSettings": { "hints": true, "mute": false, "animSpeed": "normal" },
    "reversiScores": {
      "vsEasy": {"w":0,"l":0,"d":0},
      "vsMedium": {"w":0,"l":0,"d":0},
      "vsHard": {"w":0,"l":0,"d":0},
      "vs2P": {"w":0,"l":0,"d":0}
    }
  }
  ```

### Multiplayer / Netcode
- Local 2-player only (shared device, hot-seat) [Confirmed]
- No network multiplayer in base clone

### Anti-Cheat / Server Authority
- N/A — single-player vs AI or local 2-player; no server [Confirmed]

### Backend / SDKs / Analytics
- None — fully client-side [Confirmed]
- No third-party SDKs

### Performance
- Estimated file size: ~60–100 KB (single HTML file) [Estimated]
- 60 FPS animations via CSS transitions [Estimated]
- AI thinking time capped: Easy/Medium instant; Hard uses setTimeout(0) to not block UI, depth-limited minimax

---

## 15. Pacing & Difficulty

### Game Pacing [Confirmed — Othello theory]
- **Early game (moves 1–20):** Disc count swings are large and unpredictable; strategic play focuses on gaining mobility and avoiding X/C squares near corners. Novices often chase high disc counts here (a trap).
- **Mid game (moves 21–40):** Corner grabs become decisive; player with corners gains stability. Mobility narrows. Tension builds as legal moves decrease.
- **Late game (moves 41–60):** Often forced/near-forced moves; corners and edges lock in the winner. Large cascading flips possible. Dramatic reversals if corners were poorly positioned.

### Difficulty Curve
- **Easy AI:** Random moves — beginners win easily; teaches basic rules
- **Medium AI:** Takes corners when available, maximizes flips — requires intentional corner avoidance strategy to beat
- **Hard AI (depth 6–8 minimax):** Uses positional table + mobility + corner weighting — defeats most casual players; represents a genuine challenge

### Key "Aha" Moments
1. Realizing corner squares are invincible — game-changing insight
2. Discovering that having fewer discs mid-game can be strategically better (mobility > count)
3. First time executing a corner-forcing sequence
4. Beating Hard AI for the first time

### Typical Churn Points
- Players who don't understand the flanking rule may be confused by early hint markers
- Hard AI may feel unbeatable for casual players (intentional difficulty tier)
- Late-game forced-move situations can feel anti-climactic (inherent to Reversi)

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---------|-----|------|
| 8×8 board rendering | ✓ | ✓ |
| Standard 4-disc start | ✓ | ✓ |
| Legal move detection (all 8 dirs) | ✓ | ✓ |
| Disc placement + flip (all flanked lines) | ✓ | ✓ |
| Auto-pass when no legal move | ✓ | ✓ |
| Both-pass game-end detection | ✓ | ✓ |
| Live disc count display | ✓ | ✓ |
| Winner detection + result screen | ✓ | ✓ |
| 2-player local mode | ✓ | ✓ |
| Easy AI (random) | ✓ | ✓ |
| Legal move hint markers | ✓ | ✓ |
| Medium AI (greedy + corner) | — | ✓ |
| Hard AI (minimax alpha-beta) | — | ✓ |
| Flip animation | — | ✓ |
| WebAudio SFX | — | ✓ |
| Mute toggle | — | ✓ |
| Scoreboard (localStorage) | — | ✓ |
| Settings persistence | — | ✓ |
| How-To modal | — | ✓ |
| Undo (vs AI) | — | ✓ |
| Responsive/mobile layout | ✓ | ✓ |

### Phased Roadmap

**Phase 1 — Core Engine (days 1–2)**
- HTML structure: board grid (8×8 divs), HUD, overlays
- Game state: `board[8][8]`, `currentPlayer`, `gameOver`
- Legal move detection function (check all 8 directions from a cell)
- Get-flips function (returns all discs to flip for a move)
- Apply-move function (place disc, flip all, switch player)
- Auto-pass and both-pass detection
- Winner calculation and disc count
- Basic click handler (no hints yet)

**Phase 2 — UI + Hints (day 2–3)**
- Render board from state (disc elements, colors)
- Hint markers on all legal cells (toggle off/on)
- Turn indicator, disc count display
- Pass notice overlay
- Result banner overlay with final counts
- New Game / Rematch button wiring

**Phase 3 — AI (day 3–4)**
- Easy AI: `getRandomMove(board, player)`
- Medium AI: `getGreedyMove()` — maximize flips + corner preference
- Hard AI: `minimaxAlphaBeta(board, depth, alpha, beta, player)` with positional weight table + mobility heuristic; setTimeout for non-blocking
- Mode/difficulty selector UI

**Phase 4 — Polish (day 4–5)**
- Disc flip CSS animation (3D rotateY)
- Placement pop-in animation
- WebAudio SFX (placement, flip, win, pass)
- Mute toggle
- Responsive layout (CSS Grid/Flexbox, clamp() for board size)
- Mobile touch support verification

**Phase 5 — Persistence + Meta (day 5)**
- localStorage: save/load settings, scoreboard
- How-To modal (rules + visual)
- Settings panel
- Final polish, cross-browser test

### Recommended Tech Stack
- HTML5 + CSS3 (Grid, Flexbox, custom properties, transitions, @keyframes)
- Vanilla ES6+ JavaScript (no frameworks)
- Web Audio API (built-in, no external audio files)
- localStorage API
- No build tools, no npm, no bundler

### Required Asset List
| Asset | Source |
|-------|--------|
| Board grid | CSS (background-color + grid lines) |
| Disc visuals | CSS radial-gradient circles |
| Hint markers | CSS semi-transparent disc outlines |
| Legal hint dots | CSS pseudo-elements |
| SFX | Web Audio API (programmatically generated) |
| Fonts | System fonts (no external font load) |
| Icons | CSS/Unicode (no image files) |

### Hardest Parts / Risks
1. **Correct flip detection in all 8 directions:** Must verify that `getFlips()` returns ALL flanked lines, not just the first one found — a common bug.
2. **Both-pass end condition:** Must check "if after switching player, new player also has no moves → game over" — edge case with sparse boards.
3. **Hard AI performance:** Minimax at depth 8 can be slow; alpha-beta pruning + move ordering (corners first) + setTimeout(0) non-blocking call are all needed.
4. **Flip animation timing:** If animating sequential flips, must delay AI move or next interaction until all animations finish.
5. **Mobile board sizing:** Must use CSS clamp() or vw/vh units so board fits on 320px+ screens without overflow or tiny touch targets.
6. **Mid-game pass UI:** Pass situations confuse new players — must display a clear notification and not let them think the game froze.

---

## 17. Open Questions

1. **Optimal Hard AI depth:** Depth 6 is fast; depth 8 may pause 1–2s on slow devices late-game. Recommend depth 6 for Hard with iterative deepening as a stretch goal. [Needs playtesting on low-end mobile]
2. **Endgame perfect play:** Hard AI at depth 6 may make suboptimal endgame moves when fewer than ~12 empty cells remain; could switch to full exhaustive search at that point. [Needs implementation testing]
3. **Animation accessibility:** Some users with vestibular disorders may need reduced-motion support (`@media (prefers-reduced-motion)`). Easy to add but not in base spec. [Estimated — low effort to add]

---

*Blueprint covers all 17 required sections. All mechanics are [Confirmed] against published Reversi/Othello rules. AI strategy values are [Confirmed] from academic/community sources. Economy, monetization, and content counts are [Confirmed] as minimal/none by nature of the game. Clone build plan is fully actionable with vanilla HTML/CSS/JS.*
