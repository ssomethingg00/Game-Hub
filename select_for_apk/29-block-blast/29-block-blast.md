# GridDrop — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text. This game is named **GridDrop**.

---

## 1. Snapshot

GridDrop is a casual block-placement puzzle played on an 8×8 grid. Players receive three polyomino pieces at a time, drag each onto the grid, fill entire rows and columns to clear them, and chase ever-higher scores. There is no time pressure and no falling — the strategic challenge is sequencing placements to maximize simultaneous line clears and combo bonuses. The game ends when none of the current three pieces can fit anywhere on the board.

**Quick facts:**
- **Reference titles:** Block Blast! (Hungry Studio, 2022), 1010! (Gram Games, 2014) [Confirmed]
- **Platforms targeted by clone:** Web (HTML5), mobile browser [Estimated]
- **Session length:** 5–20 minutes [Estimated]
- **Play style:** Active, single-player, turn-based puzzle [Confirmed]
- **Age/content rating:** IARC 3+ / PEGI 3 / Everyone [Confirmed]
- **Monetization model (original):** Ad-supported with optional IAP; clone targets ad-free/free-to-play [Estimated]

---

## 2. Core Loops

- **30-second loop:** Pick one of three tray pieces → drag it to a valid empty region on the 8×8 grid → release to place → watch any completed rows/columns flash and clear → score updates.
- **Session loop:** Place pieces until all three are used (triggering a new tray of three) → keep clearing lines to open space → try to top personal best before the board fills and triggers game-over.
- **Meta loop (original):** Return daily for login rewards and to beat previous best score; no persistent campaign — score is the only long-term metric. Clone equivalent: localStorage best score + optional daily-challenge seed. [Estimated]

---

## 3. Mechanics, Controls & Game States

### Core Rules
- **Board:** 8×8 grid of cells; each cell is empty or filled. [Confirmed]
- **Piece tray:** 3 polyomino pieces displayed below (or beside) the board at all times. [Confirmed]
- **Placement:** Player drags a piece onto the board. A piece may only be placed where every cell it occupies is currently empty and within grid bounds. Rotation is NOT allowed. [Confirmed]
- **Clearing:** After each placement, every row AND every column that is now completely filled is cleared simultaneously. Points are awarded for all clears triggered by that single placement. [Confirmed]
- **Refill:** When all 3 tray pieces have been placed (tray is empty), a new set of 3 random pieces is generated. Pieces are never refilled mid-tray. [Confirmed]
- **Game over:** Occurs when, after generating (or while holding) a set of pieces, NONE of the remaining unplaced pieces can legally fit anywhere on the board. The check is performed: for each remaining piece, try every board position — if at least one fits for at least one piece, play continues. [Confirmed]

### Controls
| Action | Desktop | Mobile/Touch |
|---|---|---|
| Pick up piece | Mouse down on tray piece | Touch-start on tray piece |
| Ghost preview | Move mouse over board | Drag with finger |
| Place piece | Release over valid cell | Lift finger over valid cell |
| Cancel placement | Release over invalid/off-board | Lift off board |
| Drag offset (mobile) | Centered on cursor | Piece rendered ~80px above finger so it's visible |

### Game Modes
- **Endless (only mode):** No rounds or levels; play continues until game-over. [Confirmed]
- *(Optional clone addition)* **Daily Challenge:** seeded RNG for reproducible piece sequence; share score.

### Game States
1. **Start screen** — title, best score, Play button, How-to overlay
2. **Playing** — board active, tray showing, HUD visible
3. **Placing** — piece held, ghost preview on board
4. **Clearing** — flash animation playing, board locked briefly
5. **Refilling** — tray animates in 3 new pieces
6. **Game Over** — overlay shows score, best, replay button

### Win / Lose
- No win state; the game is endless. [Confirmed]
- Lose: no remaining piece fits anywhere → Game Over overlay. [Confirmed]
- On game-over, board and score are wiped; best score persists. [Confirmed]

### Feedback Systems
- Ghost preview shows piece at hover position: green tint = valid, red tint = invalid [Estimated]
- Cell pop / scale animation on piece placement [Estimated]
- Flash + fade animation on line clear, delay ~300ms [Estimated]
- Combo text ("Combo x2!", "BLAST!") pops over board [Estimated]
- Score counter animates up [Estimated]
- WebAudio: place click, line clear chime, combo whoosh, game-over tone [Estimated]

---

## 4. Progression

- **No XP/levels:** Score is the sole progression metric. [Confirmed]
- **Best score:** Saved to localStorage; shown on HUD and game-over screen. [Estimated]
- **Difficulty curve:** Implicit — as the board fills, pieces become harder to fit; the player's own placements create the difficulty. [Confirmed]
- **No upgrade trees, prestige, or unlock systems** in the core game. [Confirmed]
- *(Original Block Blast adds cosmetic themes/skins as IAP unlocks.)* [Confirmed]

---

## 5. Economy & RNG

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Score points | In-session only | Placing pieces + clearing lines | N/A (display only) |
| Best score | Persistent | Session end if new best | N/A |

No soft/hard currency in the core gameplay loop. Original app adds coins for cosmetics — omitted in clone. [Estimated]

### Scoring Formula [Estimated]

| Event | Points |
|---|---|
| Place a piece (per cell) | 1 pt per cell |
| Clear 1 row or column | 10 pts per line |
| Clear 2 lines simultaneously | 20 pts + 10 combo bonus = 30 pts total |
| Clear 3 lines simultaneously | 30 pts + 30 combo bonus = 60 pts total |
| Clear N lines simultaneously | N×10 + (N-1)×10 combo = N×10 + N×10 - 10 |
| Simplified combo formula | lines_cleared × 10 × combo_multiplier; multiplier = lines_cleared |

**Combo multiplier table:**
| Simultaneous Lines Cleared | Base Pts | Combo Bonus | Total |
|---|---|---|---|
| 1 | 10 | 0 | 10 |
| 2 | 20 | 20 | 40 |
| 3 | 30 | 60 | 90 |
| 4 | 40 | 120 | 160 |
| 5+ | N×10 | N×(N-1)×10 | N²×10 |

*Assumption: combo bonus scales quadratically — each additional simultaneous clear multiplies the reward. Tune via playtesting.*

### RNG / Piece Generation [Estimated]
- Pieces drawn from a weighted pool of ~20 standard polyomino shapes (see Section 6).
- No guaranteed balance per tray; fully random each refill.
- Minimum piece complexity guard: avoid generating 3 large pieces simultaneously when board > 60% full (optional difficulty assist).

---

## 6. Content Inventory

### Polyomino Piece Set (clone target: 20 shapes) [Estimated]

| ID | Name | Shape | Cells |
|---|---|---|---|
| P01 | Dot | 1×1 | 1 |
| P02 | Domino-H | 1×2 horizontal | 2 |
| P03 | Domino-V | 2×1 vertical | 2 |
| P04 | Triomino-L | L-tromino | 3 |
| P05 | Triomino-I | 1×3 horizontal | 3 |
| P06 | Triomino-IV | 3×1 vertical | 3 |
| P07 | Square-2x2 | 2×2 block | 4 |
| P08 | Tetro-I-H | 1×4 horizontal | 4 |
| P09 | Tetro-I-V | 4×1 vertical | 4 |
| P10 | Tetro-L | L-tetromino | 4 |
| P11 | Tetro-J | J-tetromino | 4 |
| P12 | Tetro-T | T-tetromino | 4 |
| P13 | Tetro-S | S-tetromino | 4 |
| P14 | Tetro-Z | Z-tetromino | 4 |
| P15 | Pento-I-H | 1×5 horizontal | 5 |
| P16 | Pento-I-V | 5×1 vertical | 5 |
| P17 | Pento-L | L-pentomino | 5 |
| P18 | Pento-P | P-pentomino (2×3 minus 1) | 5 |
| P19 | Big-L | 3×3 minus 6 (corner L) | 3 |
| P20 | Square-3x3 | 3×3 full block | 9 |

### Board
- 1 board: 8×8 = 64 cells [Confirmed]

### Themes / Cosmetics (clone MVP)
- 1 default color palette (dark background, neon accent blocks)
- Optional: 3 palette swaps (unlockable or free) [Estimated]

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract grid / geometric puzzle — no story, no characters. [Confirmed]
- **Premise:** Pure score-chasing; "beat your best." [Confirmed]
- **Tone:** Calm, satisfying, minimalist. Clearing lines delivers tactile pleasure ("juicy" feedback). [Confirmed]
- **Color palette:** Deep navy/charcoal background; vivid jewel-tone blocks (cyan, magenta, amber, lime, violet); white grid lines. [Estimated]
- **Writing style:** Minimal UI copy; short punchy labels ("COMBO!", "BLAST!", "Game Over"). [Confirmed]
- **Licensed IP:** None. [Confirmed]

---

## 8. Meta & Social Systems

- **Daily rewards:** Not present in core game; optional clone addition (first-play-of-day bonus). [Estimated]
- **Achievements:** None in core; clone can add localStorage-based achievements (e.g., "Clear 4 lines at once"). [Estimated]
- **Leaderboards:** Not present offline; optional integration with a free leaderboard API. [Estimated]
- **Multiplayer:** None. [Confirmed]
- **Events / seasons:** Original ships seasonal cosmetic packs; clone omits. [Estimated]
- **Live-ops cadence:** Original: weekly small updates, monthly events. Clone: static content, no live-ops burden. [Estimated]
- **Social sharing:** Share score as text/image (optional). [Estimated]

---

## 9. UI / UX & Screen Map

### Screens
| Screen | Purpose |
|---|---|
| Start / Title | Logo, best score display, Play button, How-to-Play button |
| How-to-Play overlay | Animated 4-step tutorial; close to return to Start |
| Gameplay | Board + tray + HUD; main play loop |
| Game Over overlay | Score summary, best score, New Game button |
| Settings modal | Sound toggle (mute/unmute), Reset Best Score |

### HUD (during gameplay)
- Current score (top center, large)
- Best score (top right, smaller)
- Combo indicator (below score, fades out after 2s)
- Tray of 3 pieces (bottom, horizontally laid out)
- Mute button (top left corner)

### Navigation Flow
```
Start → [Play] → Gameplay ←→ [Game Over] → [New Game] → Gameplay
Start → [How To] → How-to overlay → [Close] → Start
Gameplay → [Mute] → toggle audio (stays in Gameplay)
```

### Onboarding / Tutorial (first launch)
1. Highlight tray: "These are your pieces — drag one onto the board."
2. Highlight first piece; animate drag motion to an empty area.
3. Show a pre-filled row: "Fill a complete row or column to clear it!"
4. Show combo example: "Clear multiple lines at once for a COMBO bonus!"
5. Dismiss: "Got it — Play!" → enter normal gameplay.

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D [Confirmed]
- **Camera:** Top-down, static — no camera movement. [Confirmed]
- **Orientation:** Portrait preferred (mobile-first); landscape works on desktop. [Estimated]
- **Art style:** Flat / neo-minimalist; rounded-corner blocks with subtle inner shadow. [Estimated]
- **Color palette:** #0f0f23 background, #1a1a3e grid, vivid block colors (6+ hues), white/light text. [Estimated]
- **Animation set:**
  - Piece pickup: slight scale-up (1.1×)
  - Ghost preview: 70% opacity, colored border
  - Placement: quick scale bounce (1.0 → 1.15 → 1.0, 120ms)
  - Line clear: row/col cells flash white then fade out over 300ms
  - Combo text: scale-in pop + float-up + fade-out, 800ms
  - Game over: board dims, overlay slides up
- **VFX:** Particle burst on combo clear (optional); screen flash on big combo. [Estimated]
- **SFX (WebAudio, no files):**
  - Piece place: soft "click" (triangle wave, 440Hz, 60ms)
  - Line clear: rising chime (sine, 880→1320Hz, 200ms)
  - Combo: bright arpeggio (3 notes, 50ms each)
  - Game over: low descending tone (sawtooth, 300→150Hz, 400ms)
  - New tray: soft whoosh (noise filter sweep, 150ms)
- **Music:** None in clone MVP (original uses looping ambient chillout track). [Estimated]

---

## 11. Monetization

**Original game (Block Blast!):** [Confirmed]
- Interstitial ads every ~3 game-overs
- Rewarded video ads: "Continue" (watch ad to add a hint/clear a row once per game)
- Banner ads on game-over screen
- IAP: Remove Ads ($2.99), Coin packs for cosmetic themes

**Clone (GridDrop) monetization plan:** [Estimated]
- No ads (clean experience)
- No IAP in MVP
- No consent/ATT flow needed (no tracking, no ads)
- Optional future: cosmetic themes at $0.99 each via a simple payment link

| Product | Type | Price |
|---|---|---|
| Remove Ads (N/A) | — | — |
| Theme Pack | Cosmetic IAP (future) | $0.99 [Estimated] |

---

## 12. Retention Hooks

- **Best score persistence:** localStorage; shown on start screen as motivation to beat it. [Estimated]
- **No energy/lives system:** play unlimited times. [Confirmed]
- **No push notifications** in web clone. [Estimated]
- **Offline earnings:** None — this is an active puzzle with no idle component. [Confirmed]
- **FOMO:** None in core game; original has limited-time cosmetic events. [Confirmed]
- **Session restart immediacy:** Game-over screen has a large "Play Again" button; friction is minimal. [Confirmed]

---

## 13. Localization & Accessibility

- **Languages:** MVP ships English-only; all UI strings in a single constants object for easy translation. [Estimated]
- **RTL support:** Not required for MVP (no text-heavy UI). [Estimated]
- **Text scaling:** Board and UI scale via CSS viewport units; no fixed font sizes. [Estimated]
- **Colorblind modes:** MVP: none. Recommended addition: block pattern differentiation (dots, stripes) in a second palette. [Estimated]
- **Difficulty assist:** Optional gentle-mode: no piece can be a 3×3 full square until score > 200. [Estimated]
- **Age/content rating:** IARC 3+ — no violence, no adult content, no data collection from minors. COPPA/GDPR-K: no user accounts, no data collected → fully compliant by default. [Confirmed]
- **Regional differences:** None for web clone. [Estimated]

---

## 14. Technical Structure

- **Engine/framework:** Vanilla JavaScript + HTML5 Canvas (or DOM grid). No build step. [Estimated]
- **Platforms:** Any modern browser (Chrome, Safari, Firefox, Edge) — desktop and mobile. [Confirmed]
- **Online/offline:** Fully offline; no network requests. [Confirmed]
- **Save system:** localStorage for best score and optionally current board state (resume on reload). [Estimated]
- **Accounts/auth:** None. [Confirmed]
- **Cross-device sync:** Not applicable. [Confirmed]
- **Multiplayer/netcode:** N/A — single-player only. [Confirmed]
- **Anti-cheat:** N/A — no competitive leaderboard in MVP. [Confirmed]
- **Backend:** None. [Confirmed]
- **Analytics:** None in MVP. [Estimated]
- **Third-party SDKs:** None. [Confirmed]
- **App size:** Single .html file, ~30–60 KB unminified. [Estimated]
- **Performance:** DOM or Canvas at 60fps on any device made after 2018. Avoid re-rendering full board every frame; only redraw changed cells. [Estimated]

---

## 15. Pacing & Difficulty

### Early Game (score 0–200)
- Board mostly empty; almost any piece placement works.
- Player learns drag-and-drop, discovers combo mechanic.
- "Aha" moment: first time two rows clear simultaneously. [Estimated]

### Mid Game (score 200–1000)
- Board starts to fill; player must think ahead.
- Piece tray variety matters — big pieces become threatening.
- Players learn to leave "columns of opportunity" for I-pieces. [Estimated]

### Late Game (score 1000+)
- Board routinely 60–80% full between clears.
- Any 3×3 or 1×5 piece can trigger game-over if space is mismanaged.
- Tension is high; each placement is deliberate. [Estimated]

### Churn Points (from original Block Blast reviews) [Confirmed]
- Players commonly churn when they realize they've trapped themselves with no valid placements, feeling "cheated" by unlucky piece RNG.
- Fix: ensure at least 1 of the 3 tray pieces is always a small piece (1–3 cells) to give an escape option. [Estimated]

### Difficulty Knobs
- Piece pool weighting (more large pieces = harder)
- Board fill rate (adjust scoring thresholds or piece size distribution)

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---|---|---|
| 8×8 board | Yes | Yes |
| 20 polyomino shapes | Yes | Yes |
| Drag-and-drop (mouse + touch) | Yes | Yes |
| Ghost preview (valid/invalid) | Yes | Yes |
| Row + column clear | Yes | Yes |
| Combo bonus scoring | Yes | Yes |
| Game over detection | Yes | Yes |
| Score + best score | Yes | Yes |
| localStorage persistence | Yes | Yes |
| Start/game-over screens | Yes | Yes |
| WebAudio SFX | Yes | Yes |
| Mute toggle | Yes | Yes |
| Responsive/mobile layout | Yes | Yes |
| Tutorial overlay | Yes | Yes |
| Particle VFX | No | Yes |
| Color themes | No | Yes |
| Daily challenge mode | No | Yes |
| Online leaderboard | No | Yes |
| Achievements | No | Yes |
| Music | No | Yes |

### Phased Roadmap

**Phase 1 — Core Engine (Days 1–2)**
- 8×8 grid data model
- Polyomino piece definitions (20 shapes)
- Piece placement logic with validity check
- Row + column completion detection and clearing
- Score calculation with combo formula

**Phase 2 — UI & Controls (Days 2–3)**
- Canvas/DOM board rendering
- Tray rendering
- Mouse drag-and-drop with ghost preview
- Touch drag-and-drop with 80px upward offset
- Snap-to-grid logic

**Phase 3 — Game Loop & Polish (Day 3–4)**
- Tray refill cycle
- Game-over detection (full piece-position sweep)
- Clear animation (flash + fade)
- Combo text pop animation
- Score counter animation

**Phase 4 — Persistence & Audio (Day 4)**
- localStorage best score
- WebAudio SFX (place, clear, combo, game-over)
- Mute toggle

**Phase 5 — Screens & Responsive (Day 5)**
- Start screen, how-to overlay, game-over overlay
- CSS viewport scaling for mobile
- Cross-browser testing

### Recommended Tech Stack
- HTML5 + CSS3 (flexbox/grid for layout)
- Vanilla JavaScript ES6+ (no frameworks)
- HTML5 Canvas for board rendering (best performance) OR CSS grid (simpler DOM)
- Web Audio API for SFX generation
- localStorage for persistence

### Required Asset List
- No external assets — all shapes defined as cell-coordinate arrays in JS
- No image files — blocks rendered as filled rectangles with CSS/Canvas
- No audio files — all SFX generated via WebAudio API

### Hardest Parts / Risks
1. **Game-over detection accuracy:** Must check every position on the board for every remaining piece, not just current piece. An O(pieces × board_cells) scan; must be correct or players get false game-overs.
2. **Touch drag offset:** Finger obscures the board; piece must render above finger with correct grid snapping. Requires transforming touch coordinates to account for offset.
3. **Simultaneous row + column clear:** A single placement can clear multiple rows AND multiple columns at the same time; the combo calculation must count ALL cleared lines in one pass before awarding points.
4. **Refill timing:** New tray should appear only after the clear animation completes, not instantly — requires async/timeout sequencing.
5. **Mobile responsiveness:** Board must scale to fit small screens while keeping tray accessible; flex layout with viewport-based sizing needed.

---

## 17. Open Questions

1. **Exact original scoring formula:** Confirmed only that clearing a line = points and simultaneous clears give combos; exact multiplier values from Hungry Studio are not public. The formula in Section 5 is [Estimated] and should be playtested for balance.
2. **Piece pool weighting in original:** Whether all 20-ish shapes are equally likely, or if small pieces appear more frequently, is unconfirmed. Equal weighting with a "small piece guarantee" rule is recommended until playtested.
3. **Board state save on reload:** Original app saves board state; web clone could do the same via localStorage JSON, but this adds complexity — deferred to post-MVP.
