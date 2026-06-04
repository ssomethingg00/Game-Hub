# BlockDrop — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.
> Game branded as **BlockDrop** — a Tetris-style falling-block stacker.

---

## 1. Snapshot

BlockDrop is a single-player falling-block puzzle game in which seven distinct tetromino shapes descend one at a time into a 10-column × 20-row well. The player repositions and rotates each piece before it locks onto the stack, then clears complete horizontal lines to score points. As score accumulates the game speeds up through levels, creating escalating pressure. The session ends when locked pieces breach the top of the well.

**Quick facts**
| Field | Value |
|---|---|
| Inspired by | Tetris (Alexey Pajitnov, 1984; Nintendo NES port 1989; Guideline standard 2001–present) |
| Platforms (clone target) | Web (HTML5 Canvas), mobile-first responsive |
| Genre | Puzzle / Arcade |
| Sub-genre | Falling-block stacker |
| Session length | 5–30 minutes |
| Play style | Active, single-player, score-chasing |
| Age/content rating | Everyone (IARC 3+) |
| Monetization model | None for this clone; ad/IAP layer optional |

---

## 2. Core Loops

- **30-second loop:** A piece spawns at the top center. Player taps/presses to move left/right, rotate, soft-drop, or hard-drop. Piece locks. Lines detected and cleared (flash + score). Next piece spawns. Repeat until game over.
- **Session loop:** Player opens the game, presses Start, survives as long as possible accumulating points across multiple levels, then sees Game Over with final score vs. personal best. Optionally restarts immediately.
- **Meta loop (minimal for arcade clone):** High score saved to localStorage. Player returns to beat their best. No persistent progression beyond score record. Optional daily-challenge or timed-sprint mode can be layered on.

---

## 3. Mechanics, Controls & Game States

### Core mechanics
1. **Spawn** — New tetromino appears at columns 3–8, row 0 (hidden row), centered. If spawn position is blocked → Game Over. [Confirmed]
2. **Move** — Shift piece left or right by 1 column per input. Blocked at walls and locked cells.
3. **Rotate** — Rotate piece 90° clockwise or counterclockwise using SRS (Super Rotation System). Five kick tests attempted in order; if all fail, rotation is canceled. [Confirmed]
4. **Soft Drop** — Piece falls at 20× normal gravity per input held. Awards 1 point per cell descended. [Confirmed]
5. **Hard Drop** — Piece teleports to lowest valid position instantly. Awards 2 points per cell dropped. [Confirmed]
6. **Ghost Piece** — Translucent copy of current piece shows where it will land. [Confirmed guideline standard]
7. **Lock** — After piece lands (contacts stack or floor), a 0.5 s lock-delay timer starts. Moving/rotating resets timer (max 15 resets). Piece locks after timer expires or if timer runs out of resets. [Confirmed Guideline]
8. **Line Clear** — Any fully filled rows are cleared simultaneously after each lock. Remaining rows shift down. [Confirmed]
9. **Hold** — Player may swap current piece to the Hold slot once per piece. Cannot hold twice in a row without placing. [Confirmed Guideline]
10. **7-Bag Randomizer** — Pieces drawn from a shuffled set of all 7 types, then the bag refills. Guarantees no more than 12 pieces between any two of the same type. [Confirmed Guideline]
11. **Level Up** — Level increments every 10 lines cleared. [Confirmed NES/Guideline]

### Core verbs
Move, Rotate, Drop, Hold, Swap

### Game modes
| Mode | Description |
|---|---|
| Marathon (main) | Endless play; speed increases with level; ends on game over |
| Sprint (optional) | Clear 40 lines as fast as possible; timer displayed |

### Input scheme
| Action | Keyboard | Mobile |
|---|---|---|
| Move left | ← or A | On-screen ◀ button / swipe left |
| Move right | → or D | On-screen ▶ button / swipe right |
| Rotate clockwise | ↑ or X | Tap playfield / on-screen ↻ button |
| Rotate counter-CW | Z | On-screen ↺ button |
| Soft drop | ↓ or S | On-screen ▼ button / swipe down slowly |
| Hard drop | Space | On-screen ⬇⬇ button / swipe down fast |
| Hold | C or Shift | On-screen Hold button |
| Pause | P or Escape | On-screen Pause button |

**Orientation:** Portrait (primary), landscape supported with side-panel repositioning.

### Win / lose / fail conditions
- **Game over:** A locked piece occupies any cell in rows 0–1 (above visible field). [Confirmed]
- **No win condition** in marathon mode — play until death; score is the achievement.
- **Failure handling:** Game Over screen shows final score, lines cleared, level reached, and personal best. "Play Again" restarts immediately (full state reset).

### AI / opponent behavior
None (single-player).

### Feedback systems
- **Visual:** Piece colors per type; ghost piece; lock flash (brief white overlay on locked cells); line-clear flash (rows flash white then vanish); level-up screen flash.
- **Audio (WebAudio generated):** Move blip, rotate click, lock thud, line clear chime (escalating: single/double/triple/tetris), level up jingle, game over tone, hard drop boom.
- **Haptic:** Mobile vibration on lock and line clear (if Vibration API available).

---

## 4. Progression

### Levels
- Starts at Level 1. Every 10 lines cleared → level increments. [Confirmed]
- No upper level cap (marathon continues indefinitely).
- Score multiplier = level number (score per clear × level). [Confirmed NES/Guideline]

### Unlock trees
None in MVP. Optional: unlock color themes at score milestones. [Estimated]

### Upgrade systems
None — purely skill-based.

### Prestige / rebirth
None.

### Gating
None — all 7 pieces available from start. No timewalls or paywalls.

---

## 5. Economy & RNG

### Currencies
No in-game currency. Score only.

### Scoring table [Confirmed NES formula adapted]

| Clear type | Base points | At Level n |
|---|---|---|
| Single (1 line) | 100 | 100 × n |
| Double (2 lines) | 300 | 300 × n |
| Triple (3 lines) | 500 | 500 × n |
| Tetris (4 lines) | 800 | 800 × n |
| Soft drop (per cell) | 1 | 1 (flat) |
| Hard drop (per cell) | 2 | 2 (flat) |

> Note: NES original uses 40/100/300/1200; Guideline uses 100/300/500/800. Blueprint uses Guideline values for better feel. [Confirmed Guideline]

### Speed curve (gravity interval in milliseconds)
[Confirmed NES-derived; converted to ms for web implementation]

| Level | ms/row | Approx G |
|---|---|---|
| 1 | 800 | 0.075 |
| 2 | 717 | 0.083 |
| 3 | 633 | 0.094 |
| 4 | 550 | 0.109 |
| 5 | 467 | 0.128 |
| 6 | 383 | 0.156 |
| 7 | 300 | 0.200 |
| 8 | 217 | 0.277 |
| 9 | 133 | 0.451 |
| 10 | 100 | 0.600 |
| 11–12 | 83 | 0.724 |
| 13–15 | 67 | 0.896 |
| 16–18 | 50 | 1.200 |
| 19–28 | 33 | 1.818 |
| 29+ | 17 | 3.529 |

### RNG
- **7-Bag randomizer:** Shuffle array of [I,J,L,O,S,T,Z], deal in order, refill and reshuffle when empty. [Confirmed Guideline]
- No gacha, no drop tables.

### Earn-vs-spend
No economy. Score is purely additive; no spending mechanic.

---

## 6. Content Inventory

### Tetrominoes (7 pieces) [Confirmed]
| Piece | Shape | Color (Guideline) | Clone color |
|---|---|---|---|
| I | ████ (4 in a row) | Cyan | Electric blue `#00D4FF` |
| J | J-shape | Blue | Royal blue `#0047FF` |
| L | L-shape | Orange | Amber `#FF8C00` |
| O | 2×2 square | Yellow | Lemon `#FFD700` |
| S | S-shape | Green | Lime `#00E676` |
| T | T-shape | Purple | Violet `#AA00FF` |
| Z | Z-shape | Red | Coral `#FF3D3D` |

### Grid
- 10 columns × 20 visible rows + 2 hidden spawn rows above. [Confirmed]

### Cell size
- Desktop: 30 px/cell → 300 × 600 px playfield.
- Mobile: scales to fill 90% of viewport height.

### Levels
- Infinite (level increments by 1 every 10 lines).

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract geometric. No world, no characters, no story. [Confirmed — Tetris has no narrative]
- **Theme:** Clean futuristic dark UI with neon-colored blocks on a dark grid. "Synthwave geometry."
- **Tone:** Focused, tense, satisfying. Rewards skilled play. Fast-paced at high levels.
- **Color palette:**
  - Background: `#0A0A1A` (near-black deep navy)
  - Grid lines: `#1A1A2E` (subtle)
  - UI text: `#E0E0FF`
  - Accent / HUD: `#00D4FF`
  - Each piece has its own vibrant neon color (see Section 6)
- **Branding:** Game is called **BlockDrop**. Tagline: *"Stack smart. Clear fast."*
- **Font:** System monospace / "Courier New" for scores; clean sans-serif for UI.
- **No licensed IP involved.**

---

## 8. Meta & Social Systems

### Missions / achievements (optional, not MVP)
- "First Tetris" (clear 4 lines at once), "Speedrunner" (reach level 10), "Survivor" (500 lines cleared) [Estimated]

### Daily challenge (optional)
- Fixed seed 7-bag sequence for the day. Score posted to in-page leaderboard. [Estimated]

### Live-ops cadence
- None required for solo web clone. Content is the game itself.

### Leaderboards
- Local high score only (localStorage). Global leaderboard requires backend (out of MVP scope).

### Multiplayer
- None in MVP. (Versus mode with garbage lines is a stretch goal.)

### Social / sharing
- "Share my score" button generates a text string for copying. [Estimated]

---

## 9. UI / UX & Screen Map

### Screen list
| Screen | Purpose |
|---|---|
| **Start Screen** | Title, best score, Start button, brief control legend |
| **Gameplay Screen** | Active game: playfield, HUD, next/hold panels |
| **Pause Screen** | Overlay on gameplay; Resume / Restart / Mute |
| **Game Over Screen** | Final score, lines, level, personal best, Play Again |
| **Settings (in-pause)** | Mute audio toggle, ghost piece toggle |

### HUD elements (during gameplay)
- Score (current)
- Best (all-time high from localStorage)
- Level
- Lines cleared
- Next piece preview (shows next 1–3 pieces)
- Hold piece slot
- Mute button (icon)
- Pause button

### Navigation flow
```
Start Screen → [Start] → Gameplay Screen
Gameplay Screen → [P/Esc] → Pause Overlay → [Resume] → Gameplay
                                            → [Restart] → Gameplay (reset)
Gameplay Screen → [Game Over] → Game Over Screen → [Play Again] → Gameplay (reset)
```

### Onboarding / tutorial (first run)
1. Start screen displays control legend (keyboard and mobile icons).
2. On first play, a brief 3-second overlay highlights: "← → Move | ↑ Rotate | Space Hard Drop."
3. Ghost piece is on by default — player intuitively learns drop target.
4. No forced tutorial; game learns by doing.

### Mobile on-screen button layout
```
[ HOLD ]          [ PAUSE ]
         [PLAYFIELD]
[ ◀ ] [↻] [↺] [ ▶ ]
     [ ▼ ]  [ ⬇⬇ ]
```
Buttons: 48 px minimum touch target. Semi-transparent overlay below playfield.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D, top-down well view.** No camera movement.
- **Canvas-based rendering.** Each cell drawn as a filled rect with 2 px inset highlight and 2 px shadow for 3D appearance.
- **Orientation:** Portrait primary. Landscape: playfield left, HUD right.

### Art style
- **Flat + slight bevel.** Each block cell rendered with:
  - Fill: piece color
  - Top/left highlight: `rgba(255,255,255,0.4)`
  - Bottom/right shadow: `rgba(0,0,0,0.4)`
  - 1 px black border
- **Ghost piece:** Same color at 20% opacity, dashed border.
- **Grid:** Subtle `#1a1a2e` lines on dark `#0a0a1a` background.
- **Locked cells:** Permanent colored blocks on board.

### Animation & VFX
- **Lock flash:** Locked cells briefly flash white (100 ms).
- **Line clear:** Cleared rows flash white → rows above collapse (150 ms animation).
- **Hard drop trail:** Ghost position sparks (particle burst, 5 small squares, 200 ms).
- **Level up:** Full-screen brief blue pulse (300 ms).
- **Game over:** Board fades to gray-scale, "GAME OVER" text fades in.

### Audio (generated via WebAudio API — no files)
| Sound | Generation method |
|---|---|
| Move blip | Short 60Hz sine, 80 ms decay |
| Rotate click | 180Hz triangle, 60 ms |
| Lock thud | 80Hz sawtooth, 120 ms with fast decay |
| Line clear (1) | 440 Hz sine chord, 300 ms |
| Line clear (4/Tetris) | 4-note ascending arpeggio, 500 ms |
| Level up | 3-note fanfare, 600 ms |
| Hard drop | 120Hz noise burst, 150 ms |
| Game over | Descending chromatic, 1 s |

### Music
- Optional: looping procedural arpeggiated synth pattern at 140 BPM using OscillatorNode. Speeds up with level. [Estimated]
- Mute toggle persists in localStorage.

### Juice / game feel
- Inputs have immediate visual response (no lag).
- Piece spawns with brief pop-in scale (1.0→1.05→1.0, 100 ms).
- Score increments animate (+N popups floating upward).
- All animations run at 60 fps via requestAnimationFrame.

---

## 11. Monetization

**MVP has no monetization.** This is a free web game.

| Type | Status |
|---|---|
| Ads (banner/interstitial/rewarded) | None in MVP |
| IAP | None |
| Subscriptions / battle pass | None |
| Consent / ATT / CMP | Not required (no tracking, no ads) |

If monetization is added later:
- Rewarded ad to continue after game over (no data collection required if using non-personalized ads).
- IAP: "Remove ads" one-time purchase if ads are added.
- GDPR/CCPA: consent popup required before any personalized ad serving.

---

## 12. Retention Hooks

### High score persistence
- Personal best saved to `localStorage['blockdrop_best']`. [Estimated — standard practice]
- Displayed prominently on Start and Game Over screens.

### Offline / idle
- Fully offline playable (no network required). No idle earnings (active gameplay only).

### Push notifications
- None (web game, no service worker push in MVP).

### FOMO / energy / lives
- None. Unlimited plays, no energy gates.

### Engagement loops
- "Just one more game" loop via score chasing.
- Level counter creates natural milestone pressure (reach level 10, 15, 20...).
- Tetris clear (4 lines) is a rare, satisfying event that rewards deliberate play.

---

## 13. Localization & Accessibility

### Languages
- English only in MVP. i18n strings isolated in a JS object for easy translation. [Estimated]

### RTL support
- Not required for initial build (layout is symmetric).

### Accessibility
- **Colorblind mode:** Each piece shape is distinguishable by shape alone; optional pattern fill overlay. [Estimated]
- **Text scaling:** UI uses relative units; score text readable at 1× system font size.
- **Keyboard-only:** Fully playable without mouse or touch.
- **Reduced motion:** Line-clear animation can be shortened via `prefers-reduced-motion` media query. [Estimated]
- **Contrast:** HUD text on dark background meets WCAG AA.

### Age/content rating
- IARC 3+ (Everyone). No violence, no in-app purchases targeting children.
- COPPA/GDPR-K: No user data collected in MVP → no compliance action needed.

### Regional differences
- None. Puzzle mechanics are universal.

---

## 14. Technical Structure

### Engine / framework
- Vanilla JavaScript + HTML5 Canvas. No build step, no framework. Single `.html` file.

### Platform
- Any modern browser (Chrome 80+, Firefox 75+, Safari 13+, Edge 80+).
- Fully offline — no network requests.

### Save system
- `localStorage` keys: `blockdrop_best` (integer score). [Estimated]
- No cloud save in MVP.

### Accounts / auth
- None.

### Cross-device sync
- None (localStorage is device-local).

### Netcode / matchmaking
- N/A (single-player).

### Anti-cheat / server authority
- N/A (single-player, no backend).

### Backend services
- None.

### Analytics
- None in MVP. Can add `navigator.sendBeacon` to a simple counter endpoint later.

### Third-party SDKs
- None.

### App size
- Target < 100 KB HTML (single file, no assets).

### Performance notes
- Canvas redrawn fully each frame at 60 fps.
- Piece logic runs in game loop separate from render.
- requestAnimationFrame for main loop; setTimeout fallback.
- Touch events via `touchstart/touchmove/touchend` on canvas.

---

## 15. Pacing & Difficulty

### Early game (Level 1–3, 0–30 lines)
- Gravity slow (800–633 ms/row). Player has time to think.
- Introduces all 7 pieces naturally via 7-bag.
- "Aha" moment: first Tetris clear (4 lines at once).

### Mid game (Level 4–9, 30–90 lines)
- Speed increases significantly. Stack management becomes critical.
- Players learn to keep the field flat and create I-piece slots.
- Common stall: players who stack too high get overwhelmed around level 7–8.

### Late game (Level 10+, 90+ lines)
- 100 ms/row at level 10 → reaction time becomes limiting factor.
- Level 20+ (33 ms/row): near-instant drop; survival requires pattern recognition.
- Scoring accelerates due to level multiplier — players chase the "big combo" at high levels.

### Churn points [Estimated from community reports]
- **Level 7–8:** Speed spike feels punishing to new players. First common quit point.
- **Level 15:** Field too full for casual players to manage.
- **Mitigation:** Ghost piece and hold piece reduce frustration significantly.

### Difficulty modes (optional)
- No difficulty select in MVP. Ghost piece and hold are always on.
- Stretch: "Hardcore" mode without ghost piece.

---

## 16. Clone Build Plan

### MVP feature checklist
- [x] 10×20 well, Canvas rendering
- [x] All 7 tetrominoes with correct shapes
- [x] SRS rotation + basic wall kicks
- [x] 7-bag randomizer
- [x] Gravity timer per level
- [x] Move / rotate / soft drop / hard drop
- [x] Ghost piece
- [x] Lock delay (0.5 s, 15 move resets)
- [x] Line detection + clear + row shift
- [x] Scoring (100/300/500/800 × level)
- [x] Level progression (every 10 lines)
- [x] Next piece preview (1–3 pieces)
- [x] Hold piece
- [x] Game over detection
- [x] Start / Pause / Game Over screens
- [x] HUD (score, best, level, lines)
- [x] localStorage high score
- [x] Mobile on-screen controls
- [x] Touch / swipe input
- [x] WebAudio SFX + mute toggle
- [x] Responsive layout (portrait + landscape)

### Full feature checklist (post-MVP)
- [ ] Sprint mode (40 lines, timed)
- [ ] T-Spin detection and bonus scoring
- [ ] Back-to-back Tetris bonus (+50%)
- [ ] Combo counter (bonus for consecutive clears)
- [ ] Procedural background music
- [ ] Color themes / skins
- [ ] Colorblind mode (pattern fills)
- [ ] Global leaderboard (requires backend)
- [ ] Daily challenge (fixed seed)
- [ ] Versus mode (garbage lines)

### Phased roadmap

**Phase 1 — Core mechanics (Day 1–2)**
- Canvas setup, cell grid, draw loop
- All 7 piece shapes + colors defined
- Spawn, move, rotate (no kicks yet), collision detection
- Lock and board update

**Phase 2 — Rotation & drops (Day 2–3)**
- SRS wall kick tables implemented
- Ghost piece calculation
- Soft drop + hard drop
- 7-bag randomizer

**Phase 3 — Line clear + scoring (Day 3)**
- Row completion detection
- Multi-row simultaneous clear
- Row-shift animation
- Score calculation (base × level)
- Level-up logic + speed update

**Phase 4 — Hold + preview (Day 3–4)**
- Hold slot logic (once-per-piece rule)
- Next-piece queue (3 shown)
- HUD rendering (score, level, lines)

**Phase 5 — UI screens (Day 4)**
- Start screen (title, best, controls legend)
- Pause overlay
- Game Over screen with restart
- localStorage best score

**Phase 6 — Mobile & polish (Day 5)**
- On-screen button overlay
- Touch/swipe handling
- Responsive layout (viewport meta, CSS scaling)
- WebAudio SFX (move, rotate, lock, clear, game over)
- VFX (lock flash, line clear flash, hard drop effect)
- Mute toggle

**Phase 7 — QA & final (Day 5–6)**
- Test all rotation/kick edge cases
- Verify line-clear shift correctness
- Confirm game over detection
- Test on mobile browsers (Chrome Android, Safari iOS)
- Verify localStorage persistence
- HTML validation

### Recommended tech stack
- HTML5 + CSS3 + Vanilla JavaScript (ES6+)
- Canvas 2D API for rendering
- Web Audio API for SFX
- No build tools, no frameworks, no dependencies
- Single `.html` file delivery

### Required asset list
- No external assets. All rendering is code-drawn.
- All audio is procedurally generated via WebAudio.
- Fonts: system fonts only.

### Hardest parts / risks
1. **SRS wall kick tables** — I-piece kicks differ from all other pieces. Must implement correctly or rotation feels broken.
2. **Lock delay + move-reset counter** — Infinite stalling exploit if not capped at 15 resets. Edge case: piece on floor vs. piece against wall.
3. **Line clear animation vs. state consistency** — Board must not advance gravity during the clear animation frame(s). Requires a brief "ARE" (appearance delay) state.
4. **Mobile swipe vs. tap disambiguation** — Swipe left/right must not trigger rotate; tap must not trigger move. Threshold: >20 px movement = swipe; else = tap.
5. **Responsive layout** — Side panels (hold, next, HUD) must reflow gracefully on very narrow phones (<360 px wide). Playfield scales to viewport height.

---

## 17. Open Questions

1. **T-Spin bonus scoring** — Exact detection algorithm (3-corner rule) should be verified against Guideline spec before implementing post-MVP T-spin bonuses. [Estimated: skip in MVP]
2. **Lock delay reset on sideways movement vs. downward** — Some implementations only reset on successful rotation, not lateral move. Community preference varies; test both and choose by feel.
3. **ARE (appearance delay)** — Classic Tetris uses a ~100 ms delay after piece lock before next spawn. Modern Guideline = 0. Test which feels better for this clone.
4. **Music tempo scaling** — Whether speeding up background music with level feels exciting or annoying requires user testing.
5. **Sprint mode target** — Is 40 lines the right sprint distance, or should it be 20? Depends on target session length.
