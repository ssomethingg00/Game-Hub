# Checkers (American Draughts) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Checkers (American Draughts) is a classic abstract strategy board game played on an 8×8 board with 12 pieces per side on dark squares only. Men move diagonally forward; reaching the far rank crowns a King that moves both directions. Captures are mandatory; multi-jump chains are forced to completion. The genre is abstract strategy / board game, squarely in the "classic" category. It is one of the most-played board games in the world, with unlimited free web and app versions.

**Quick facts:**
- **Origin:** Traditional folk game (English Draughts variant codified ~1756); no single developer/publisher.
- **Platforms:** Web, iOS, Android, PC, every gaming platform.
- **Release / last update:** Public domain ruleset; clone can be built fresh.
- **Rating / Install scale:** Millions of plays daily across platforms. [Confirmed]
- **Age / content rating:** ESRB E (Everyone); suitable all ages; no COPPA/GDPR-K issues if no accounts or ads.
- **Monetization model (original):** Varies by clone — free-to-play with optional ads, no IAP needed; this blueprint targets an ad-free, no-IAP local web game.

---

## 2. Core Loops

- **30-second loop:** Select a piece → highlighted legal moves appear → tap destination → piece slides/jumps → opponent (or AI) responds → repeat.
- **Session loop:** Choose mode (vs AI / 2-player) + difficulty → play one full game (typically 5–20 minutes) → see result banner → restart or switch difficulty.
- **Meta loop:** Improve skill over successive games; track win/loss score in localStorage; try harder AI difficulties; play against a friend locally.

---

## 3. Mechanics, Controls & Game States

### Board Setup [Confirmed]
- 8×8 board, 64 squares; only the 32 dark squares are used.
- Each player starts with 12 pieces (men) on the three rows closest to them.
- Dark squares are playable; pieces never occupy light squares.
- Standard orientation: Player 1 (red/dark) at bottom, Player 2 / AI (black/light) at top.

### Men Movement [Confirmed]
- Men move diagonally forward only, one square at a time.
- "Forward" = toward the opponent's back rank.

### Capture (Jump) [Confirmed]
- A man jumps over an adjacent enemy piece diagonally, landing on the empty square beyond.
- The captured piece is removed immediately (or held until the jump chain completes — standard American rules remove immediately).
- Capture is **mandatory**: if any capture is available, the player must take it.
- When multiple capture sequences are available, the player may choose which one to execute.

### Multi-Jump Chaining [Confirmed]
- After a successful jump, if the same piece can jump again from its new position, **it must continue jumping**.
- The chain cannot be stopped mid-sequence (American standard rules).
- Exception: if a man lands on the king row mid-jump, **the turn ends immediately** — the piece is crowned King but cannot continue jumping until the next turn. [Confirmed]

### Kinging [Confirmed]
- A man that reaches the opponent's back row (row 8 for Player 1, row 1 for Player 2) becomes a King.
- Kings are visually marked (crown/double-piece symbol).
- Kings move **diagonally in all four directions**, one square per move.
- Kings capture in all four diagonal directions.
- Kings must still obey mandatory capture and multi-jump rules.

### Win / Lose / Draw Conditions [Confirmed]
- **Win:** Opponent has no pieces remaining, OR opponent has no legal moves on their turn.
- **Loss:** You have no pieces, or you have no legal moves.
- **Draw:** Agreed draw (optional UI button); or no capture/kinging for 40 consecutive moves [Estimated — standard 40-move rule]; or same board state repeated 3 times [Estimated].

### Game Modes [Confirmed]
1. **vs AI** — Single player vs computer; three difficulty levels.
2. **2-Player Local** — Two humans, same device, alternating turns.

### AI Difficulty [Estimated]
- **Easy:** Random legal move selection (prefers captures but otherwise random).
- **Medium:** Minimax depth 4 with alpha-beta pruning; capture-aware heuristic.
- **Hard:** Minimax depth 8 with alpha-beta pruning, iterative deepening; advanced evaluation (position value, mobility, kings, back-row defense).

### Input Scheme [Confirmed]
- **Click / Tap** piece to select → legal moves highlight → click/tap destination square to move.
- Touch and mouse both supported.
- During AI turn, all board interaction is disabled.
- Landscape and portrait both work; board scales responsively.

### Feedback Systems [Estimated]
- Selected piece: highlighted border/glow.
- Legal move squares: colored dot or ring overlay.
- Move animation: smooth CSS transition of piece.
- Capture: captured piece fades/pops off.
- Kinging: crown appears with brief glow or bounce animation.
- Win: result banner + confetti or glow effect.
- Web Audio API SFX: move click, jump/capture, kinging fanfare, win jingle.
- Mute toggle persists to localStorage.

---

## 4. Progression

Checkers has no in-game progression system — it is a pure skill game. [Confirmed]

- **No XP / levels / upgrades / unlocks.**
- **Skill progression:** Players naturally improve by playing harder AI levels.
- **Persistence layer (clone-specific):** localStorage tracks cumulative wins/losses/draws per mode and per difficulty; displayed as a scoreboard.
- **Difficulty unlock:** All difficulties available from the start (no gating). [Estimated]

---

## 5. Economy & RNG  *(tables)*

Checkers has no in-game economy or currency system. [Confirmed]

| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| N/A | — | — | — |

**Cost scaling:** None — not applicable.

**RNG / drop rates:** No RNG beyond AI move selection at Easy difficulty (random tie-breaking). [Confirmed]

**Score tracking (clone-specific, Estimated):**

| Tracked Stat | Scope | Storage |
|---|---|---|
| Wins | Per mode + difficulty | localStorage |
| Losses | Per mode + difficulty | localStorage |
| Draws | Per mode + difficulty | localStorage |
| Last difficulty | Global | localStorage |
| Mute setting | Global | localStorage |

---

## 6. Content Inventory  *(counts + lists)*

| Category | Count | Notes |
|---|---|---|
| Board | 1 | 8×8, 32 playable dark squares |
| Piece types | 2 | Man, King |
| Player sides | 2 | Red (bottom), Black (top) |
| Game modes | 2 | vs AI, 2-Player Local |
| AI difficulty levels | 3 | Easy, Medium, Hard |
| SFX | 5 | Move, Capture, King, Win, Lose |
| UI screens | 4 | Main menu/setup, Game board, Result banner, How-to modal |

No levels, characters, items, enemies, or collections beyond the above. [Confirmed]

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract classic board game — no story or world.
- **Visual theme:** Traditional checkerboard — dark red/brown and cream/tan squares; red and black pieces.
- **Narrative:** None — pure mechanical skill game. [Confirmed]
- **Tone:** Calm, competitive, timeless. Clean and dignified rather than flashy or cartoonish.
- **IP:** Public domain mechanics and visual language. No licensing needed. [Confirmed]
- **Color palette:** Board dark squares #8B4513 (saddlebrown) or #769656 (chess green variant); light squares #F0D9B5 or #FFFACD; Player 1 pieces: #CC2200 (red); Player 2 pieces: #1A1A1A (near-black); King crown: #FFD700 (gold); highlight: #00CC44 (green dots).

---

## 8. Meta & Social Systems

- **No daily missions, events, battle pass, or seasonal content.** [Confirmed — pure classic game]
- **No multiplayer network / matchmaking / guilds.** [Confirmed — local only for this clone]
- **No sharing or referral features.** [Estimated — out of scope for this clone]
- **Local scoreboard:** Win/loss/draw per difficulty and mode stored in localStorage. Displayed in UI. [Estimated]
- **Live-ops cadence:** None required — single-session self-contained game.

---

## 9. UI / UX & Screen Map

### Screen List

| Screen | Purpose |
|---|---|
| **Setup / Main Menu** | Choose mode (vs AI / 2-player), difficulty selector, New Game button, How-to button, scoreboard display |
| **Game Board** | Full playable board with HUD (turn indicator, captured piece counts, mute toggle) |
| **Result Banner** | Overlay on board showing win/loss/draw message, Play Again and Change Mode buttons |
| **How-To Modal** | Overlay explaining rules: movement, mandatory capture, kinging, multi-jump, win conditions |
| **Settings (inline)** | Mute toggle in HUD; mode/difficulty selectors above board |

### Settings / Options Menu Contents
- Mute / unmute SFX (toggle button in HUD)
- Mode selector: vs AI / 2-Player (top of page or modal)
- Difficulty selector: Easy / Medium / Hard (visible when vs AI selected)
- New Game button (resets board)

### Gameplay HUD Elements
- Turn indicator: "Red's Turn" / "Black's Turn" / "AI Thinking…"
- Captured pieces count: Red captured X | Black captured X
- Mute toggle icon
- New Game button

### Navigation Flow
```
Setup screen → [New Game] → Game Board → [game ends] → Result Banner → [Play Again] → Game Board
                                                                       → [Change Mode] → Setup screen
Game Board → [How-To] → How-To Modal → [Close] → Game Board
```

### Onboarding / Tutorial Steps [Estimated]
1. On first load, How-To modal auto-shows briefly OR a highlight callout points to "How to Play."
2. Player 1 turn indicator highlights; callout: "Click a red piece to select it."
3. Legal move squares highlight automatically upon selection.
4. After first move: "Captures are mandatory — jump over enemy pieces when possible."
5. After first capture: "After a jump, if another jump is available, you must keep jumping!"
6. On first king: "You've been crowned King! Kings move in all directions."
7. Tutorial ends — free play continues.

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D, top-down orthographic. [Confirmed]
- **Camera / perspective:** Fixed overhead view of the full board. No scrolling or zooming.
- **Display orientation:** Portrait on mobile (board fills screen); landscape on desktop (board centered with sidebar for UI).
- **Art style:** Flat / clean vector aesthetic; classic woodgrain or solid-color squares; circular pieces with subtle gradient/shadow; Kings display a crown icon or inner ring.
- **Color palette:** Classic — dark red/mahogany squares, cream light squares, red + black pieces, gold crowns.
- **Animation:**
  - Piece move: 150ms CSS translateX/Y or absolute position tween.
  - Piece capture: 200ms fade-out + scale-down.
  - Kinging: 300ms scale-up + golden glow pulse.
  - Selected piece: CSS box-shadow ring / pulsing ring.
  - Legal move hints: semi-transparent green circles on valid squares.
- **VFX:** Subtle board highlight on hover; result screen glow/confetti using canvas particles. [Estimated]
- **SFX (Web Audio API, no external files):** [Estimated]
  - Move: short soft click (oscillator pop, ~80ms).
  - Capture: slightly louder crack/thud (noise burst, ~100ms).
  - Kinging: ascending short fanfare (two-tone, ~400ms).
  - Win: short victory jingle (3-note ascending, ~600ms).
  - Lose: short descending 2-note, ~400ms.
- **Music:** None (keeping it calm and classic; mute toggle still provided for SFX).

---

## 11. Monetization

This blueprint targets a free, ad-free, no-IAP local web game. [Confirmed — per build requirements]

| Ad Type | Placement | Frequency |
|---|---|---|
| None | — | — |

| IAP Product | Price | Content |
|---|---|---|
| None | — | — |

- **No loot boxes, gacha, or subscriptions.** [Confirmed]
- **No ATT prompt, GDPR/CMP consent popup, or age-gate required** (no ads, no tracking, no personal data collection). [Confirmed]
- **Monetization aggressiveness:** None.

---

## 12. Retention Hooks

- **No daily rewards, login streaks, or push notifications.** [Confirmed — classic game, no live service]
- **No offline/idle earnings.** [Confirmed]
- **No energy/lives system.** [Confirmed]
- **Retention via intrinsic motivation:** Difficulty ladder (Easy → Medium → Hard), local scoreboard showing cumulative wins/losses, natural "one more game" loop after close matches.
- **FOMO mechanics:** None.

---

## 13. Localization & Accessibility

- **Languages:** English only for this clone. [Estimated — can be expanded]
- **RTL support:** Not required for English-only build. [Estimated]
- **Text scaling:** UI uses relative units (em/rem); board scales with CSS clamp(). [Estimated]
- **Colorblind mode:** [Estimated] Distinguish pieces by shape in addition to color (King crown marker provides secondary cue); optionally offer a high-contrast piece color toggle (red vs blue instead of red vs black).
- **Difficulty assist:** Three difficulty levels serve as accessibility for skill level.
- **Touch accessibility:** Tap targets sized ≥ 44px per WCAG 2.1 guidelines. [Estimated]
- **Age / content rating:** ESRB E / PEGI 3 equivalent. No mature content.
- **COPPA / GDPR-K compliance:** Not required — no accounts, no data collection, no ads, pure offline game. [Confirmed]
- **Regional differences:** None — universal game.

---

## 14. Technical Structure

- **Engine / framework:** Vanilla JavaScript, HTML5, CSS3 — no dependencies, no build step. [Confirmed — per requirements]
- **Platforms:** Web browser (desktop + mobile); runs offline by double-click.
- **Online / offline:** Fully offline. [Confirmed]
- **Save system:** localStorage for scoreboard and settings (mute, last-used difficulty/mode).
- **Accounts / auth:** None. [Confirmed]
- **Cross-device sync:** None. [Confirmed]
- **Multiplayer netcode:** None — local 2-player only. [Confirmed]
- **Anti-cheat:** N/A — single-device, no competitive stakes. [Confirmed]
- **Backend services:** None. [Confirmed]
- **Analytics / A/B testing:** None. [Confirmed]
- **Third-party SDKs:** None. [Confirmed]
- **App size:** Single .html file, estimated 50–100 KB unminified. [Estimated]
- **Performance:** Board state is a 64-element array; minimax at depth 8 with alpha-beta prunes to manageable search tree (~milliseconds per move on modern hardware). Web Workers optional for Hard AI to prevent UI blocking. [Estimated]

### Core Data Structures [Estimated]
```
board[64]       // 0=empty, 1=P1 man, 2=P1 king, 3=P2 man, 4=P2 king
selectedSquare  // index or null
legalMoves[]    // [{from, to, captures[]}]
currentPlayer   // 1 or 2
mode            // 'ai' | 'local'
difficulty      // 'easy' | 'medium' | 'hard'
```

### AI Algorithm [Estimated]
```
function minimax(board, depth, alpha, beta, maximizing):
  if depth==0 or terminal: return evaluate(board)
  moves = getLegalMoves(board, player)
  for move in moves:
    board2 = applyMove(board, move)
    score = minimax(board2, depth-1, alpha, beta, !maximizing)
    alpha/beta update + pruning
  return best score

evaluate(board):
  score = 0
  for each piece:
    if P1 man:  score += 100
    if P1 king: score += 175  // kings worth ~1.75x
    if P2 man:  score -= 100
    if P2 king: score -= 175
    // positional bonuses: center control, back-row defense, advancement
```

---

## 15. Pacing & Difficulty

### Early Game (moves 1–10)
- Both sides develop pieces from back three rows.
- AI at Easy makes random/suboptimal moves, often leaving captures open.
- AI at Medium/Hard develops toward center aggressively.

### Mid Game (moves 10–25)
- Capture exchanges begin; multi-jumps become available.
- Skilled play focuses on forcing favorable exchange sequences.
- Hard AI correctly evaluates piece trades and king races.

### Late Game (moves 25+)
- King vs. King endgames; maneuvering for position.
- No-legal-moves win condition becomes relevant.
- Hard AI handles endgame well due to deeper search depth.

### Difficulty Curve [Estimated]
| Difficulty | Depth | Win Rate vs Novice | Win Rate vs Expert |
|---|---|---|---|
| Easy | 1 (random) | ~20% | ~5% |
| Medium | 4 | ~60% | ~25% |
| Hard | 8 | ~85% | ~50% |

### Key Milestone Moments
- First capture: player discovers mandatory-capture rule.
- First multi-jump: satisfying chain capture.
- First kinging: power-up feel.
- Win by no-moves: surprising but rules-correct win condition.

### Churn Points [Estimated]
- Hard AI at depth 8 may feel unbeatable to casual players — consider showing "AI wins: X" count to normalize it.
- Mandatory capture can feel punishing when forced into a bad sequence — a brief "Why?" tooltip helps.

---

## 16. Clone Build Plan

### MVP Feature Set
- [x] 8×8 board, correct square coloring, piece placement
- [x] Man movement (diagonal forward, one square)
- [x] Mandatory capture enforcement
- [x] Multi-jump chain (forced continuation)
- [x] Kinging (mid-jump termination rule)
- [x] King movement (all four diagonals)
- [x] Legal move generation and highlighting
- [x] Turn alternation
- [x] Win / no-legal-moves detection
- [x] vs AI (Easy/Medium/Hard minimax)
- [x] 2-Player local mode
- [x] Result banner + restart
- [x] Touch + mouse input

### Full Feature Set (above + these)
- [x] Web Audio SFX (move, capture, king, win, lose)
- [x] Mute toggle
- [x] localStorage scoreboard
- [x] Responsive / mobile-first layout
- [x] How-to modal
- [x] Piece selection highlight + legal move dots
- [x] Move animations
- [ ] Draw by repetition / 40-move rule (optional polish)
- [ ] Undo last move (optional)
- [ ] AI difficulty description tooltips

### Phased Roadmap

**Phase 1 — Board & Rendering (Day 1)**
- HTML/CSS board grid (8×8, dark/light squares)
- Piece rendering (circles, king crown indicator)
- Responsive scaling with CSS clamp()

**Phase 2 — Game Logic (Day 1–2)**
- board[] state array
- getLegalMoves(board, player): generates all man + king moves + jumps
- Mandatory capture detection (if any jump available, only jumps returned)
- Multi-jump chain logic (force continuation when further jumps exist)
- Kinging on rank entry + mid-jump termination
- Win / no-moves detection
- applyMove(board, move) → new board state

**Phase 3 — UI & Interaction (Day 2)**
- Click/tap selection → highlight selected piece
- Show legal destination squares as dots
- Click destination → execute move (chain jumps if multi-jump)
- Turn indicator, captured count display
- Mode and difficulty selectors
- New Game button

**Phase 4 — AI (Day 2–3)**
- Easy: random move from legal set
- Medium: minimax depth 4 + alpha-beta + capture-preferring eval
- Hard: minimax depth 8 + alpha-beta + positional eval
- AI runs on setTimeout to allow UI update before thinking

**Phase 5 — Polish (Day 3)**
- CSS move/capture animations
- Web Audio SFX generation (oscillator-based)
- Mute toggle
- localStorage scoreboard
- How-to modal
- Result banner with Play Again
- Mobile touch testing

**Phase 6 — QA (Day 3–4)**
- Test mandatory capture in all positions
- Test multi-jump chains (2, 3, 4 jumps)
- Test mid-jump kinging termination
- Test king all-direction movement + capture
- Test win by no-pieces and win by no-legal-moves
- Test AI all difficulty levels (legal moves only, reasonable play)
- Test mode switch and restart
- Test touch on mobile viewport
- Verify no console errors
- Verify file ends with `</html>`

### Recommended Tech Stack
- HTML5 + CSS3 + Vanilla JavaScript (ES6+)
- No frameworks, no build tools
- Web Audio API for SFX
- localStorage API for persistence
- CSS Grid for board layout
- CSS custom properties for theming

### Required Asset List
- No external assets — all generated in code:
  - Board: CSS Grid
  - Pieces: CSS border-radius circles + inline SVG crown for kings
  - SFX: Web Audio API oscillator synthesis
  - Colors: CSS custom properties

### Hardest Parts / Risks
1. **Multi-jump chain enforcement:** After a jump, legal-moves must be re-evaluated for the same piece only, and UI must prevent selecting a different piece mid-chain.
2. **Mandatory capture detection:** Every call to getLegalMoves must first scan for jumps; if any exist, only jumps are returned (not simple moves).
3. **Mid-jump kinging rule:** When a man lands on the back rank during a jump sequence, the chain stops and the piece is crowned — even if additional jumps would be geometrically possible.
4. **AI depth at Hard without blocking UI:** minimax at depth 8 can take 100–500ms; use `setTimeout(aiMove, 50)` to let the browser render first; consider Web Workers if needed.
5. **King backward captures with multi-jump:** King can jump in any direction and chain; need to ensure the already-captured piece is not counted a second time in the same chain (ghost piece problem — track captured squares in the current jump path).

---

## 17. Open Questions

1. **Draw rule implementation:** Should the 40-move rule (no capture/kinging) be enforced? The original game supports it but many casual implementations skip it. [Estimated: implement as optional, disabled by default]
2. **King value in evaluation:** Exact king-vs-man weight ratio needs playtesting — 175:100 is a standard starting point. [Estimated]
3. **AI latency on Hard on low-end mobile:** Depth 8 may exceed 1 second on older phones. Iterative deepening with a time limit (e.g. 1500ms) is more robust than fixed depth. [Estimated — needs mobile testing]
4. **Colorblind mode specifics:** Whether to use shape only, or also offer a color-swap palette, depends on user feedback. [Estimated]
