# Tic-Tac-Toe (with AI) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Tic-Tac-Toe is the definitive abstract strategy paper-and-pencil game played on a 3×3 grid where two players alternate placing their symbol (X or O) and the first to get three in a row (horizontal, vertical, or diagonal) wins. This implementation adds a computer AI opponent with three difficulty tiers, 2-player local mode, a persistent scoreboard, and polished browser UI. Its core appeal is instant accessibility, zero learning curve, and the satisfying depth-vs-simplicity tension that makes even a "solved" game engaging through the AI challenge.

**Quick facts:** Genre: Abstract strategy / casual puzzle. Platforms: Web browser (desktop + mobile). Origin: Traditional/public domain, first digital version 1952 (OXO by Alexander Douglas). Target session length: 1–3 minutes per round. Age rating: ESRB E (Everyone) / PEGI 3 — no content concerns. Monetization model (clone): none required; free offline play.

---

## 2. Core Loops

- **30-second loop:** Click/tap an empty cell → symbol appears with animation → AI responds (or second player taps) → check for win/draw → result displayed → click "New Game."
- **Session loop:** Choose mode (vs AI / 2-player) + difficulty + who plays X → play 3–6 rounds → watch scoreboard update → optionally reset scores and switch settings.
- **Meta loop:** No long-term progression beyond the persistent scoreboard. Retention comes from the desire to beat the Hard AI (provably impossible to win against, achievable to draw with perfect play) and competitive local multiplayer. [Estimated]

---

## 3. Mechanics, Controls & Game States

### Core Rules [Confirmed]
- 3×3 grid, 9 cells.
- Players alternate placing their symbol (X or O) each turn.
- First to place three of their symbol in any row, column, or diagonal wins.
- If all 9 cells are filled with no winner, the game is a draw.
- X always traditionally goes first (configurable in this implementation).

### Win Conditions [Confirmed]
Eight winning lines: 3 rows (0-1-2, 3-4-5, 6-7-8), 3 columns (0-3-6, 1-4-7, 2-5-8), 2 diagonals (0-4-8, 2-4-6).

### Game Modes [Confirmed]
| Mode | Description |
|---|---|
| vs AI Easy | Human vs computer; AI plays random legal moves |
| vs AI Medium | Human vs computer; AI plays random ~40% of turns, minimax 60% |
| vs AI Hard | Human vs computer; AI uses full minimax — provably unbeatable |
| 2-Player Local | Two humans alternate on same device |

### Controls [Confirmed]
- **Primary:** Click or tap any empty cell to place symbol.
- Filled cells: disabled (no interaction).
- During AI turn: all cells disabled until AI response completes (~300–500ms delay for UX feel).
- "New Game" button: resets board, keeps scoreboard.
- "Reset Scores" button: clears scoreboard.
- Mode / difficulty / symbol / first-player selectors: available before and between rounds.
- Mute toggle: silences all audio.

### Input & Orientation [Confirmed]
- Portrait and landscape both supported (responsive grid).
- Mobile touch + desktop mouse/keyboard.
- Screen orientation: any (fluid layout).

### Win / Lose / Draw Handling [Confirmed]
- **Win:** Winning line highlighted with distinct color + glow animation; result banner shows winner; victory sound plays; winner's score incremented.
- **Draw:** All cells dimmed; "It's a Draw!" banner; draw sound; draw counter incremented.
- **No "lives" or penalty system** — just start a new round.
- On new round: board clears, symbols reset, turn order restarts per settings.

### AI Behavior [Confirmed via algorithm analysis]

**Easy (Random):** AI picks uniformly at random from all empty cells. Can be beaten easily.

**Medium (Mixed):** AI uses minimax with ~60% probability, random move 40% of time. Creates mistakes the human can exploit.

**Hard (Minimax — Unbeatable):**
- Full recursive minimax algorithm over all possible board states.
- Scoring: AI win = +10, Human win = −10, Draw = 0.
- At each node: maximizing player (AI) picks highest score; minimizing player (Human) picks lowest.
- With alpha-beta pruning optionally (not required for 3×3 — full tree depth ≤ 9 half-moves).
- Result: AI never loses. Best human outcome is draw with perfect play.
- AI picks center (cell 4) if available on first move as optimal opening. [Estimated / known optimal]

### Feedback Systems [Confirmed]
- Visual: cell highlights on hover, symbol pop-in animation on placement, winning line glow.
- Audio: placement click sound, win fanfare, draw sound, all via WebAudio API (no files).
- Turn indicator text updates in real time.
- Result banner overlays board on game end.

---

## 4. Progression

Tic-Tac-Toe has no in-game progression system. [Confirmed]

| Element | Detail |
|---|---|
| Scoreboard | Running tally of X wins / O wins / Draws; persists via localStorage |
| Difficulty tiers | Easy → Medium → Hard; serve as informal "progression" of challenge |
| No XP / levels | N/A for this game type |
| No unlocks | All modes available from launch |
| No prestige | N/A |

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| None | — | — | — |

No currency system. Game is entirely free to play, no purchases, no timers. [Confirmed]

### RNG
| Mode | RNG Use |
|---|---|
| Easy | 100% random move selection from legal moves |
| Medium | 40% random, 60% optimal (random float per turn) |
| Hard | No RNG — fully deterministic minimax |
| 2-Player | No RNG |

### Cost Scaling
Not applicable — no economy. [Confirmed]

---

## 6. Content Inventory *(counts + lists)*

| Category | Count | Detail |
|---|---|---|
| Board states | 5,478 unique legal positions | 255,168 terminal paths [Confirmed] |
| Symbols | 2 | X and O |
| Winning lines | 8 | 3 rows, 3 columns, 2 diagonals |
| Game modes | 4 | Easy AI, Medium AI, Hard AI, 2-Player |
| Difficulty levels | 3 | Easy, Medium, Hard |
| Screens / views | 1 | Single-page app, all UI in one HTML |
| Sound effects | 3 | Place, win, draw (WebAudio generated) |
| Themes/skins | 1 | Default dark theme with accent palette |

---

## 7. Theme, Narrative & Tone

**Setting:** Abstract / minimal. No world or story — the game IS the board.

**Premise:** None. Pure competitive abstract game with zero narrative. [Confirmed]

**Visual tone:** Clean, modern, slightly playful. Dark background with vibrant X/O accent colors (e.g., cyan for X, coral/orange for O). Rounded cells with subtle shadows.

**Writing style:** Terse UI labels only. Button text ("New Game", "Reset Scores", "Mute"). No dialogue.

**Overall tone:** Casual-competitive. Relaxed but satisfying. No violence, no story, no licensed IP.

**Character "personalities":** X and O have color identities only — no avatars or characters needed.

---

## 8. Meta & Social Systems

| System | Present | Detail |
|---|---|---|
| Scoreboard | Yes | Persistent X wins / O wins / Draws via localStorage |
| Daily missions | No | N/A for this game type |
| Achievements | No | Could be added (e.g., "Beat Medium AI") [Estimated optional] |
| Leaderboards | No | Local only; no server |
| Multiplayer (online) | No | Local 2-player only |
| Sharing | No | Not included in base spec |
| Events | No | N/A |
| Battle pass | No | N/A |
| Live-ops | None | No ongoing content — fully static game |

---

## 9. UI / UX & Screen Map

### Screen List
| Screen / Section | Purpose |
|---|---|
| Header | Game title + mute toggle |
| Mode + Settings Bar | Select mode (vs AI / 2P), difficulty, player symbol, who goes first |
| Turn Indicator | Shows whose turn it is; updates live |
| Game Board | 3×3 interactive grid; main gameplay area |
| Result Banner | Win / draw / loss announcement overlay; "New Game" CTA |
| Scoreboard | X wins / O wins / Draws counters; "Reset Scores" button |
| How-to-Play | Collapsible rules section at bottom |

*(Single-page app — no separate screens/routes; all sections always visible in scrollable layout)*

### Settings / Options (in-page controls)
- Mode selector: "vs AI" / "2 Player"
- Difficulty selector (visible when vs AI): Easy / Medium / Hard
- Your symbol: X / O
- First move: You / AI (or Player 1 / Player 2 in 2P mode)
- Mute toggle (header)
- Reset Scores button

### Gameplay HUD Elements
- Current turn label ("X's Turn" / "O's Turn" / "AI is thinking…")
- 3×3 cell grid with hover states and placed symbols
- Result overlay with winner text and New Game button

### Navigation Flow
```
Page Load → Load localStorage settings & scores
         → Render board in initial state
         → Player selects settings (optional)
         → Player clicks cell → symbol placed
         → AI turn (if applicable) with delay
         → Win/Draw check → Result banner OR continue
         → "New Game" → board reset, new round
         → "Reset Scores" → scoreboard zeroed
```

### Onboarding / Tutorial
1. Page loads with board visible and empty — no splash screen needed.
2. "How to Play" section at bottom explains rules in 3 bullet points.
3. Mode and difficulty controls are labeled clearly above the board.
4. First-time hint: turn indicator says "X's Turn — click any cell to start."
5. No forced tutorial — the game teaches itself through immediate play. [Estimated]

---

## 10. Art, Audio, Camera & Feel

**Dimension:** 2D flat. [Confirmed]
**Camera / Perspective:** Top-down orthographic (standard HTML/CSS layout). [Confirmed]
**Orientation:** Responsive — portrait mobile primary, landscape desktop secondary.

**Art Style:** Flat design with soft shadows and rounded corners. Minimal, modern. No illustrations or characters.

**Color Palette:** [Estimated]
| Element | Color |
|---|---|
| Background | #1a1a2e (deep navy) |
| Board cell background | #16213e |
| Board cell border | #0f3460 |
| X symbol | #00d4ff (cyan) |
| O symbol | #ff6b6b (coral) |
| Winning line highlight | #ffd700 (gold) glow |
| Button primary | #e94560 |
| Text | #eaeaea |

**Animation Set:**
- Symbol pop-in: scale 0 → 1.1 → 1.0 with ease-out (200ms)
- Cell hover: background lighten, cursor pointer
- Winning line: pulse/glow keyframe animation on three winning cells
- Result banner: fade-in + scale-up
- AI "thinking" indicator: pulsing turn label

**VFX:** CSS keyframe animations only. No canvas particles needed. Winning cells glow gold.

**SFX (WebAudio API, no external files):** [Confirmed — generated programmatically]
| Sound | Trigger | Generation method |
|---|---|---|
| Place | Any cell click | Short 220Hz click burst (0.05s) |
| Win | Win detected | Two-tone ascending arpeggio (0.3s) |
| Draw | Draw detected | Low descending tone (0.3s) |

**Music:** None. Background music not needed for this game type. [Estimated — appropriate for format]

**Game Feel / Juice:**
- Immediate visual feedback on tap/click (no lag)
- AI delay (300ms) feels natural, not instantaneous
- Winning cells animate distinctly from rest of board
- Clear color differentiation between X and O

---

## 11. Monetization

**Monetization model: None.** [Confirmed — public domain game, standalone HTML file]

| Type | Present | Notes |
|---|---|---|
| Ads | No | Not applicable for offline HTML file |
| IAP | No | No store, no purchases |
| Subscription | No | N/A |
| Loot boxes / gacha | No | N/A |
| ATT prompt | No | No tracking, no network requests |
| GDPR/CMP | No | No personal data collected |
| Age gate | No | ESRB E / PEGI 3 — no age verification needed |

The clone is a fully free, offline, no-monetization experience. If deployed to web with ads, standard GDPR consent banner would be needed.

---

## 12. Retention Hooks

| Hook | Present | Detail |
|---|---|---|
| Persistent scoreboard | Yes | Survives browser close via localStorage |
| Settings persistence | Yes | Mode, difficulty, symbol saved to localStorage |
| Idle / offline earnings | No | N/A — not an idle game |
| Daily rewards | No | N/A |
| Push notifications | No | N/A |
| Energy / lives | No | Unlimited play |
| FOMO mechanics | No | N/A |
| Streak tracking | No | Not included (could add "win streak" counter) [Estimated optional] |

Primary retention driver: trying to beat the Hard AI (draw is the ceiling), local rivalry in 2-player mode, and the quick session loop encouraging "one more game." [Confirmed by genre norms]

---

## 13. Localization & Accessibility

**Languages supported:** English only (base implementation). [Estimated]
All UI strings are short labels — easy to localize by replacing string constants.

**RTL support:** Not needed for base English build; CSS `direction: rtl` toggle would suffice if added. [Estimated]

**Text scaling:** Relative units (rem/em, viewport units) ensure browser zoom works correctly.

**Colorblind modes:** X and O are differentiated by BOTH color AND symbol shape, so colorblind users are fully supported without additional modes. [Confirmed — best practice]

**Difficulty assist:** Easy mode serves as accessibility tier for new/younger players.

**Reduced motion:** Should respect `prefers-reduced-motion` media query for animations. [Estimated — standard accessibility]

**Screen reader:** Cells labeled with `aria-label` (e.g., "Row 1, Column 1, empty"). Turn indicator uses `aria-live` region. [Estimated — good practice for web]

**Age / content rating:** ESRB E (Everyone) / PEGI 3. No violence, no adult content, no chat, no user-generated content. No COPPA/GDPR-K concerns — no data collection of any kind.

**Regional differences:** None — public domain game, no regional pricing or content variation.

---

## 14. Technical Structure

**Engine / Framework:** Vanilla HTML5 + CSS3 + JavaScript (ES6+). No frameworks, no build step, no dependencies. [Confirmed — per spec]

**Platforms:** Any modern browser (Chrome, Firefox, Safari, Edge). Works offline — no network requests. [Confirmed]

**File structure:** Single `.html` file with inline `<style>` and `<script>`.

**Save system:** `localStorage` for:
- `ttt_scores` — JSON: `{x: N, o: N, draws: N}`
- `ttt_settings` — JSON: `{mode, difficulty, playerSymbol, firstPlayer, muted}`

**Accounts / Auth:** None — fully anonymous, no login. [Confirmed]

**Cross-device sync:** None — local browser storage only. [Confirmed]

**Multiplayer netcode:** None — local same-device 2-player only. [Confirmed]

**Anti-cheat / server authority:** N/A — single-player / local-only game. [Confirmed]

**Backend services:** None. Fully static. [Confirmed]

**Analytics:** None. [Confirmed]

**Third-party SDKs:** None. [Confirmed]

**App size:** ~15–25 KB total (HTML + inline CSS + JS). [Estimated]

**Performance:** 9-cell board + minimax over max 9 moves = trivially fast. No performance concerns. Minimax on 3×3 is <1ms computation. [Confirmed]

**Browser APIs used:**
- `localStorage` (persistence)
- `AudioContext` (WebAudio SFX)
- CSS animations (transitions, keyframes)
- Pointer events (touch + mouse unified)

---

## 15. Pacing & Difficulty

**Early game (first session):** Player discovers placement, sees win detection, tries Easy AI. Wins quickly. [Estimated]

**Mid game (subsequent sessions):** Player tries Medium AI, sees it make mistakes, can win ~50% of time with average play. [Estimated]

**Late game / mastery:** Player challenges Hard AI. With perfect play, always draws. The realization that Hard AI is truly unbeatable is the "wall" — retention shifts to local 2-player or personal best streaks. [Estimated]

**Difficulty progression:**
| Difficulty | Expected Human Win Rate |
|---|---|
| Easy | ~80–90% |
| Medium | ~40–60% |
| Hard | 0% (draw is best possible) |

**Key milestone / "aha" moment:** First time player draws against Hard AI — realizing both perfect strategies cancel out. [Estimated]

**Churn point:** Players who cannot beat Hard AI (which is everyone) may disengage; mitigation is medium mode as satisfying challenge and 2-player local mode for social play. [Estimated]

**Round duration:** 30 seconds to 2 minutes per game. Sessions typically 3–10 minutes. [Estimated]

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---|---|---|
| 3×3 board rendering | ✓ | ✓ |
| X/O placement + turn logic | ✓ | ✓ |
| Win detection (all 8 lines) | ✓ | ✓ |
| Draw detection | ✓ | ✓ |
| Winning line highlight | ✓ | ✓ |
| Easy AI (random) | ✓ | ✓ |
| Hard AI (minimax) | ✓ | ✓ |
| Medium AI (mixed) | — | ✓ |
| 2-player local mode | ✓ | ✓ |
| Scoreboard | ✓ | ✓ |
| localStorage persistence | — | ✓ |
| Symbol choice (X/O) | — | ✓ |
| First-player choice | — | ✓ |
| WebAudio SFX | — | ✓ |
| Mute toggle | — | ✓ |
| Mobile responsive | ✓ | ✓ |
| Win animation | — | ✓ |
| New Game / Reset Scores | ✓ | ✓ |
| How-to-Play section | — | ✓ |

### Phased Roadmap

**Phase 1 — Core game engine (Day 1)**
- HTML structure: board grid, controls, scoreboard
- CSS: responsive layout, cell styling, X/O display
- JS: cell click handler, turn logic, win/draw detection
- Winning line highlight
- New Game functionality

**Phase 2 — AI implementation (Day 1–2)**
- Easy AI: random legal move selector
- Hard AI: minimax recursive function with terminal state scoring
- AI move delay (setTimeout 300–500ms for UX)
- Disable board during AI turn

**Phase 3 — Modes & settings (Day 2)**
- Mode toggle (vs AI / 2P)
- Difficulty selector (Easy/Medium/Hard)
- Symbol selector (X/O)
- First-player selector
- Medium AI (probability-mixed between random and minimax)

**Phase 4 — Polish & persistence (Day 2–3)**
- localStorage save/load for scores + settings
- WebAudio SFX (place, win, draw)
- Mute toggle
- Win animation (CSS keyframes)
- Result banner
- Mobile touch testing + viewport meta
- How-to-play collapsible section

**Phase 5 — QA (Day 3)**
- Test all 8 winning lines
- Test draw detection
- Verify Hard AI never loses (test all opening moves)
- Test mode/difficulty switches mid-game (should reset board)
- Test localStorage persistence across refreshes
- Cross-browser test (Chrome, Firefox, Safari, Edge)
- Mobile test (iOS Safari, Android Chrome)

### Recommended Tech Stack
- **Language:** Vanilla JavaScript (ES6+) — no frameworks needed
- **Styling:** CSS3 with custom properties (variables) for theming
- **Audio:** Web Audio API (no external files)
- **Storage:** localStorage API
- **Delivery:** Single `.html` file, inline everything
- **No build tools required**

### Required Asset List
| Asset | Source |
|---|---|
| X symbol | CSS text or SVG path (inline) |
| O symbol | CSS text or SVG path (inline) |
| UI sounds (3) | Programmatically generated via WebAudio |
| Color palette | CSS custom properties |
| Fonts | System font stack (no external fonts) |

**No external assets needed — 100% self-contained.**

### Hardest Parts / Risks

1. **Minimax correctness** — Must correctly handle all terminal states and not over-prune. Test by playing every possible opening move against Hard AI; all should result in draw or AI win, never human win.
2. **AI move timing UX** — Instant AI response feels jarring; too long feels broken. 300–500ms delay is the sweet spot.
3. **Mode/difficulty switch behavior** — Must decide: switch takes effect immediately (resetting board) or after current round ends. Resetting immediately is simpler and expected.
4. **Mobile touch responsiveness** — Cells must be large enough to tap accurately (~80px minimum). Board must not overflow viewport.
5. **Symbol/first-player permutations** — When AI plays as X (goes first), logic must correctly trigger AI on game start without waiting for human input.

---

## 17. Open Questions

1. **Win streak tracking** — Should the scoreboard also display current win streaks? Not specified; easy to add. [Estimated: omit from base, add as minor feature]
2. **AI "personality"** — Should Easy AI occasionally block wins rather than being purely random? Pure random is simpler and still feels "easy." [Estimated: pure random for Easy is correct]
3. **Board size variants** — 4×4 or 5×5 variants possible but out of scope for this spec. [Not planned]
4. **Sound design specifics** — Exact WebAudio frequencies/envelopes need playtesting to sound good without external files. [Estimated: short tones as specified in Section 10]

---

*Blueprint complete. All 17 sections filled. No TBD gaps. Economy is N/A (confirmed). Anti-cheat is N/A (local-only). Minimax algorithm is well-documented and unbeatable on 3×3. Build estimated at 2–3 days solo developer.*
