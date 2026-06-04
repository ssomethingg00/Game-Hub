# Tower of Hanoi — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Tower of Hanoi is a single-player logic puzzle in which the player moves a stack of N differently-sized disks from the leftmost peg to the rightmost peg, obeying two constraints: only one disk may be moved at a time, and a larger disk may never rest on top of a smaller one. The appeal is the elegant mathematical depth hidden under simple rules — the minimum solution always equals 2^N − 1 moves, giving players a concrete target to chase. It is simultaneously a child-friendly introduction to recursion and a meditative brain-teaser for adults.

**Quick facts:**
- **Origin:** Invented by French mathematician Édouard Lucas, 1883. [Confirmed]
- **Genre:** Pure logic / combinatorial puzzle.
- **Comparable games:** Towers of London (neuropsychology test), Rubik's Cube (multi-state constraint puzzle), Peg Solitaire, Lumosity puzzles.
- **Target audience:** Ages 8+ through adult; students, puzzle enthusiasts, casual gamers.
- **Session length:** 1–15 minutes depending on disk count (3 disks ≈ 30 sec optimal; 8 disks ≈ 5–20 min).
- **Play style:** Active, single-player, turn-based, no time pressure (timer is informational).
- **Platforms (clone target):** Browser (HTML5), fully offline-capable.
- **Age/content rating:** IARC 3+ / ESRB E. [Estimated] No violence, mature themes, or monetization pressure.
- **Monetization model (clone):** None — pure free puzzle game.

---

## 2. Core Loops

**30-second loop:**
Player picks the top disk on one peg → selects a legal destination peg → disk animates across → move counter increments → player scans board state and picks next disk.

**Session loop:**
Choose disk count → play puzzle to completion → compare move count vs. optimal (2^N − 1) → view win screen → optionally change disk count or watch auto-solve demo → start new game.

**Meta loop (minimal for a pure puzzle):**
Beat personal best moves/time per disk count (stored in localStorage) → unlock the satisfaction of achieving optimal play → try harder disk counts (3 → 8).

---

## 3. Mechanics, Controls & Game States

### Core Rules [Confirmed]
1. Three vertical pegs (source, spare, target); all disks start stacked on the leftmost peg, largest at bottom, smallest at top.
2. Only the top disk of any peg may be moved.
3. A disk may only be placed on an empty peg OR on top of a larger disk.
4. Goal: rebuild the full sorted stack on the rightmost peg.

### Core Verbs
- **Select** — tap/click a peg to pick its top disk (highlights it).
- **Place** — tap/click a destination peg to drop the selected disk.
- **Drag** — drag a disk directly from one peg to another.
- **Cancel** — tap/click the same peg again (or press Escape) to deselect.
- **New Game** — reset the puzzle with the current (or newly selected) disk count.
- **Auto-Solve** — trigger animated recursive solution demo.

### Game Modes
| Mode | Description |
|---|---|
| Manual Play | Player solves the puzzle manually (tap or drag). |
| Auto-Solve Demo | Recursive algorithm animates the solution step-by-step. |
| Speed Run | Same as manual play; timer emphasizes time-to-solve. |

*(Speed Run is just manual play with the timer highlighted — no separate mode needed in the engine.)*

### Input Scheme
| Action | Desktop | Mobile |
|---|---|---|
| Select disk | Click top disk or peg base | Tap disk or peg |
| Place disk | Click destination peg | Tap destination peg |
| Drag disk | Mouse drag from disk to peg | Touch drag |
| Cancel selection | Click selected peg / Esc key | Tap selected peg |
| New Game | Click button | Tap button |
| Change disk count | Click selector buttons (3–8) | Tap selector |

**Orientation:** Portrait-primary on mobile (pegs stack vertically or in a single row depending on viewport); landscape also supported on desktop.

### Win / Fail States
- **Win:** All N disks on the rightmost peg, sorted largest-to-smallest. Show win overlay with move count, optimal count, time elapsed, and best-ever record.
- **Illegal move:** Attempted placement of larger disk onto smaller → flash red shake animation on target peg, play error sound, no move recorded.
- **No lose state:** Player can always keep trying; no time limit punishment.

### Feedback Systems
- **Selection highlight:** Selected peg glows; top disk lifts slightly (CSS transform).
- **Illegal move:** Target peg shakes red briefly; error sound plays.
- **Legal move:** Disk animates sliding from source to destination; satisfying "thud" sound.
- **Win:** Confetti burst, win chime, overlay appears.
- **Auto-solve:** Each step animates with a short delay between moves (configurable speed).

---

## 4. Progression

### Difficulty via Disk Count [Confirmed]
| Disks (N) | Minimum Moves (2^N−1) | Approx. Solve Time (optimal play) |
|---|---|---|
| 3 | 7 | ~20 sec |
| 4 | 15 | ~45 sec |
| 5 | 31 | ~2 min |
| 6 | 63 | ~5 min |
| 7 | 127 | ~10 min |
| 8 | 255 | ~20+ min |

### Unlock / Gating
- All disk counts (3–8) available from the start. [Estimated — standard for puzzle clones]
- Progression comes from personal-best tracking (best moves + best time per N).

### Prestige / Reset
- None. Starting a new game always resets the current puzzle; personal bests persist.

---

## 5. Economy & RNG

### Currencies
None. This is a pure puzzle with no in-game economy. [Confirmed]

### RNG
None. The puzzle is fully deterministic. The starting configuration is always the canonical sorted stack on peg 1. [Confirmed]

### Scoring / Metrics (used in place of economy)
| Metric | Formula | Stored |
|---|---|---|
| Move count | Increments by 1 per legal move | Session + localStorage best |
| Optimal moves | 2^N − 1 | Computed, not stored |
| Efficiency % | (Optimal / Actual) × 100 | Session display |
| Time elapsed | Seconds since first move | Session + localStorage best |

---

## 6. Content Inventory

| Category | Count | Notes |
|---|---|---|
| Pegs | 3 | Fixed; named Source, Spare, Target |
| Disk-count presets | 6 | N = 3, 4, 5, 6, 7, 8 |
| Game modes | 2 | Manual Play, Auto-Solve Demo |
| Disk color scheme | 1 set | Rainbow gradient, largest = warm, smallest = cool |
| Sound effects | 4 | Pick-up, place (legal), illegal move, win chime |
| Screens | 4 | Main game, win overlay, settings/options panel, tutorial tooltip |

---

## 7. Theme, Narrative & Tone

**Setting:** Abstract / geometric. Three pegs on a wooden-looking base, colorful disks. No explicit world or characters.

**Narrative premise:** Inspired loosely by the legend of the Brahmin priests moving golden disks in a temple — the puzzle will be "complete" when moved optimally. The clone need not reference this lore unless desired; it adds flavor but is not mechanically required.

**Tone:** Calm, focused, meditative. Clean and minimal UI to reduce cognitive noise and let the puzzle breathe. Slight "satisfying" game-feel (smooth animations, pleasant sounds) to reward each move.

**Art style:** Modern flat design with depth cues — slightly rounded rectangles for disks, subtle drop shadows, gradient fills on disks for visual hierarchy.

**Writing style:** Terse, helpful UI labels ("Move 12 / Optimal: 7", "New Game", "Auto-Solve"). No dialogue.

---

## 8. Meta & Social Systems

**Personal bests:** Best move count and best time per disk count, stored in localStorage. Displayed in win overlay and HUD. [Estimated — standard for offline puzzle games]

**Daily challenge / missions:** Not present in base game; could add a "Disk of the Day" preset as a nice-to-have. [Estimated]

**Leaderboards / multiplayer:** None in offline clone. Could integrate a simple REST leaderboard API later.

**Social sharing:** Win overlay could include a "Share result" button generating a shareable text string (e.g., "I solved 5-disk Tower of Hanoi in 32 moves! Optimal is 31."). [Estimated nice-to-have]

**Live-ops cadence:** None required for a static offline puzzle. No ongoing content burden.

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Main Game | Primary gameplay — pegs, disks, HUD, controls |
| Win Overlay | Displays results, best comparison, replay/new options |
| Settings Panel | Sound toggle, auto-solve speed, about info |
| How-to-Play Tooltip | First-run or on-demand rules reminder |

### HUD Elements (during gameplay)
- **Moves:** Current move count
- **Optimal:** 2^N − 1 target
- **Time:** MM:SS elapsed (starts on first move)
- **Best Moves / Best Time:** Personal best for current N (from localStorage)
- **Disk count selector:** 3 | 4 | 5 | 6 | 7 | 8 (tappable)
- **New Game button**
- **Auto-Solve button** (disabled during auto-solve)
- **Mute button** (toggle audio)

### Navigation Flow
```
[Load page]
     |
[Main Game] ←→ [Settings Panel]
     |              |
[Win Overlay] ──→ [Main Game (reset)]
     |
[Auto-Solve Demo] → auto-returns to idle
```

### Onboarding / Tutorial (first run)
1. A subtle tooltip appears over peg 1: "Click a peg to pick up its top disk."
2. Player clicks peg 1 → disk highlights. Tooltip advances: "Now click another peg to place it."
3. Player completes first move → tooltip: "Great! Never place a larger disk on a smaller one."
4. Tooltip disappears after move 3 (or on dismiss). [Estimated — standard puzzle onboarding]
5. A "?" button in the corner re-opens the rules modal at any time.

### Settings / Options
- Sound on/off toggle
- Auto-solve animation speed (Slow / Normal / Fast)
- Reset personal bests (confirm dialog)
- About / credits

---

## 10. Art, Audio, Camera & Feel

**Dimension:** 2D flat.
**Camera/perspective:** Static front view; pegs are vertical rectangles, disks are horizontal bars.
**Display orientation:** Portrait on mobile (3 pegs side by side, each taking ~30% width). Landscape on desktop.

**Art style:**
- Flat with subtle shadows (box-shadow on disks).
- Pegs: dark brown rounded-rectangle pillars on a wide base plate.
- Base plate: warm wood-tone gradient (#8B5E3C → #C4884F). [Estimated]
- Disks: rainbow color palette based on disk index (largest = red/orange, smallest = violet/blue). Disk width proportional to its index; height constant.
- Background: soft gradient from deep navy (#1a1a2e) to indigo (#16213e) — gives a satisfying contrast with colorful disks.

**Color palette (estimated):**
| Element | Color |
|---|---|
| Background | #1a1a2e → #16213e (gradient) |
| Peg post | #5C3317 |
| Peg base | #8B5E3C |
| Disk 1 (largest) | #E74C3C (red) |
| Disk 2 | #E67E22 (orange) |
| Disk 3 | #F1C40F (yellow) |
| Disk 4 | #2ECC71 (green) |
| Disk 5 | #3498DB (blue) |
| Disk 6 | #9B59B6 (purple) |
| Disk 7 | #1ABC9C (teal) |
| Disk 8 (smallest) | #E91E63 (pink) |
| HUD text | #ECF0F1 |
| Win overlay | rgba(0,0,0,0.85) |

**Animation:**
- Disk pick-up: lifts up via translateY (50ms ease-out).
- Disk place: slides horizontally then drops (CSS transition, 250ms).
- Auto-solve: sequential move animations with 400ms inter-move delay (adjustable).
- Illegal move: peg shake (CSS keyframe, 3 oscillations, 300ms total, red flash).
- Win: CSS confetti particles burst from center of screen.

**VFX:**
- Confetti on win (JS-generated colored div particles, fade out).
- Disk "shadow" deepens on lift.

**SFX (WebAudio, no files):**
- Pick-up: soft "click" (short sine burst, ~80Hz).
- Legal place: satisfying "thud" (low-freq impulse ~100Hz, short decay).
- Illegal: harsh buzz (sawtooth wave, ~200Hz, 150ms).
- Win chime: ascending 5-note melody (sine waves, C-E-G-C-E).

**Music:** None (optional ambient loop could be added).

---

## 11. Monetization

**None.** This is a pure offline puzzle game with no ads, IAP, loot boxes, subscriptions, or battle pass. [Confirmed — design decision for the clone]

**Consent / ATT / CMP flows:** Not required; no ads, no user tracking, no third-party SDKs. No personal data is collected. Only localStorage is used for personal bests, scoped to the user's own device.

**Age/content compliance:** No COPPA or GDPR-K obligations since no data is collected or transmitted.

---

## 12. Retention Hooks

**Personal bests (primary hook):** Best move count and time per disk count displayed in HUD and win screen. The proximity between current performance and personal best creates organic re-engagement. [Estimated]

**Idle/offline earnings:** None — not applicable for a discrete-session puzzle. [Confirmed]

**Push notifications:** None (offline HTML file, no service worker required for basic version).

**Difficulty ladder:** Natural escalation from 3 disks → 8 disks keeps players engaged across multiple sessions.

**Auto-solve demo:** Watching the algorithm solve a hard puzzle creates a "can I beat that?" motivation.

---

## 13. Localization & Accessibility

**Languages (MVP):** English only. [Estimated — can be expanded via string table]

**RTL support:** Not required for MVP (UI labels are short and mostly numeric).

**Text scaling:** Relative units (em/rem/vw) throughout; scales with browser text size settings.

**Colorblind support:** Disks labeled with their size number (1 = largest) inside the disk to supplement color coding. Patterns or hatching could be added as an enhancement.

**Motor accessibility:**
- Both tap-to-select AND drag-and-drop — players with limited motor control can use tap (requires less precision).
- Large tap targets for pegs (minimum 48×48 px effective touch area).
- Keyboard support: Tab to cycle pegs, Enter/Space to select, Escape to cancel.

**Difficulty assist:** Illegal-move highlighting helps novices learn without penalty. Auto-solve demo teaches the algorithm.

**Age/content rating:** IARC 3+ / ESRB E. No mature content, violence, or monetization. COPPA/GDPR-K: N/A (no data collection). [Estimated]

**Regional differences:** None. Puzzle mechanics are universal.

---

## 14. Technical Structure

**Engine/framework:** Vanilla HTML5 + CSS3 + JavaScript (ES6+). No build tools, no frameworks. [Confirmed — per requirements]

**Platforms:** Any modern browser (Chrome, Firefox, Safari, Edge) on desktop and mobile. Works fully offline after initial load.

**File structure:** Single self-contained `.html` file with inline `<style>` and `<script>`. Runs by double-click.

**Save system:** `localStorage` — keys: `toh_best_moves_N` and `toh_best_time_N` for each disk count N ∈ {3..8}. No account/auth required.

**Accounts/auth:** None.

**Cross-device sync:** Not supported (localStorage is device-local by design).

**Multiplayer/netcode:** None — single-player only.

**Anti-cheat:** N/A (single-player, no external leaderboard; client-side only).

**Backend services:** None.

**Analytics:** None.

**Third-party SDKs:** None. All audio generated via Web Audio API (built into browser).

**Performance notes:**
- Disk count max = 8; at most 8 DOM elements in play. No performance concerns.
- Animations via CSS transitions + requestAnimationFrame for auto-solve sequencing.
- Target: 60fps on any device manufactured after 2016.

**Approx. file size:** ~40–80 KB (single HTML file, no assets).

---

## 15. Pacing & Difficulty

### Early Game (3–4 disks)
- 3 disks: solvable in 7 moves; most players succeed within 2–3 attempts without knowing the algorithm. The aha-moment: "just move the top N-1 out of the way."
- 4 disks: 15 moves, requires a bit more planning. Players realize the recursive structure intuitively.

### Mid Game (5–6 disks)
- 5 disks (31 moves): planning horizon lengthens significantly. Players who haven't internalized the algorithm start making suboptimal moves and need 40–60+ moves.
- 6 disks (63 moves): a real challenge; average untrained player may take 100–200+ moves. The puzzle starts to feel long.

### Late Game (7–8 disks)
- 7 disks (127 moves): demands full algorithmic understanding to approach optimal. Casual players may give up and use Auto-Solve.
- 8 disks (255 moves): even optimal play takes ~5–10 minutes at a brisk pace. This is the expert tier.

### Key Milestone ("aha") Moments
1. First solve of any disk count — win overlay appears.
2. Matching or beating the optimal move count — special "Perfect!" label.
3. Beating a personal best in either moves or time.
4. Watching auto-solve and recognizing the pattern.

### Churn Points [Estimated]
- Players who don't understand the recursive algorithm stall at 5 disks and may abandon.
- Mitigation: Auto-Solve demo teaches the pattern visually; "Hint" button (optional) showing the next correct move.

---

## 16. Clone Build Plan

### MVP Feature Checklist
- [x] 3 pegs rendered with disks stacked on peg 1
- [x] Disk count selector (3–8)
- [x] Tap-to-select → tap-to-place interaction
- [x] Drag-and-drop interaction
- [x] Legal-move enforcement (top-disk only, no larger-on-smaller)
- [x] Illegal-move visual + audio feedback
- [x] Move counter + optimal display
- [x] Timer (starts on first move)
- [x] Win detection + win overlay
- [x] Personal best (localStorage)
- [x] New Game button
- [x] Responsive layout (mobile + desktop)
- [x] WebAudio SFX (4 sounds)
- [x] Mute toggle

### Full Feature Checklist (post-MVP)
- [ ] Auto-solve animated demo with speed control
- [ ] Confetti win animation
- [ ] Keyboard navigation (Tab + Enter)
- [ ] Colorblind disk labels
- [ ] First-run tutorial tooltips
- [ ] Settings panel (sound speed, reset bests)
- [ ] Share result button
- [ ] "Perfect!" badge for optimal solve

### Phased Roadmap

**Phase 1 — Core Puzzle (Day 1)**
Build peg+disk rendering, disk-count selector, tap interaction, legal-move enforcement, move counter, win detection.

**Phase 2 — Polish & Controls (Day 1–2)**
Add drag-and-drop, timer, localStorage personal bests, win overlay, New Game.

**Phase 3 — Juice & Audio (Day 2)**
Add WebAudio SFX, CSS animations (pick/place/shake/confetti), mute toggle, responsive layout.

**Phase 4 — Auto-Solve Demo (Day 2–3)**
Implement recursive solver, animate solution with configurable step delay, disable manual input during solve.

**Phase 5 — Accessibility & Polish (Day 3)**
Keyboard nav, disk labels, tutorial tooltip, settings panel, final cross-browser testing.

### Recommended Tech Stack
- HTML5 + CSS3 (Flexbox + CSS custom properties)
- Vanilla JavaScript ES6+ (no dependencies)
- Web Audio API (no external audio files)
- localStorage API
- Touch Events API (for mobile drag)
- CSS transitions + keyframe animations

### Required Asset List
- No external assets needed (all shapes via CSS, all audio via WebAudio).
- Font: system-ui stack or a single Google Font (optional; can be inlined if needed).

### Hardest Parts / Risks
1. **Dual input (tap + drag) without conflicts:** Touch events can fire alongside mouse events; must prevent double-handling. Use `pointer` events or carefully separate touch/mouse handlers with a `dragging` flag.
2. **Auto-solve animation sequencing:** The recursive algorithm generates all moves instantly; must convert to an async step-by-step queue with delays. Use a `Promise`-based or `setTimeout`-queue approach, not recursion with sleep.
3. **Drag hit detection on mobile:** Disk drag needs to recognize which peg the disk is dropped onto using `elementFromPoint` at touchend, since native drag-and-drop doesn't work on iOS.
4. **Disk width proportionality:** Ensuring disks visually represent size hierarchy clearly at all screen widths, especially for 8 disks where the narrowest disk needs a minimum visible width.
5. **Win detection edge case:** Must check the target peg (peg index 2) has exactly N disks in sorted order only after every move — not just "N disks exist somewhere."

---

## 17. Open Questions

1. **Hint system:** Whether to show the "next optimal move" as a hint button is a game-design call that may affect replayability vs. educational value. Recommend implementing it as a toggleable feature. [Needs playtesting preference]
2. **4-peg variant (Frame-Stewart):** The classic 3-peg version is specified; a 4-peg optional mode would be a significant scope expansion and is left out of this blueprint.
3. **Optimal detection for non-standard starting configurations:** If a future version adds mid-puzzle saves/loads, optimal move counting becomes more complex. For now, optimal = 2^N − 1 from the canonical start state. [Not applicable to MVP]

---

*Blueprint prepared for Game #28 — Tower of Hanoi. All mechanics [Confirmed]; economy, UI flows, and art values [Estimated] from puzzle genre norms and Tower of Hanoi mathematical literature.*
