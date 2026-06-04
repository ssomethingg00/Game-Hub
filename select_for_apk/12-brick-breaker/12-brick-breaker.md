# Brick Breaker (Breakout) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Brick Breaker is a single-player arcade game in which the player controls a paddle at the bottom of the screen, bouncing a ball upward to destroy a grid of colored bricks. The game combines reaction timing, angle control, and light strategy. Clearing all bricks advances the player to the next level with increased difficulty. Letting the ball fall below the paddle costs a life; losing all lives ends the game.

**Quick facts:**
- **Original:** Atari Breakout (1976, Atari Inc.); genre lineage continued by Arkanoid (1986, Taito), and hundreds of modern mobile clones.
- **Platforms:** Arcade (original), Web, iOS, Android, PC (modern clones).
- **Session length:** 5–20 minutes per sitting [Estimated].
- **Age/content rating:** ESRB E (Everyone) — no mature content.
- **Monetization model (modern clones):** Ad-supported free + optional IAP; this blueprint targets a pure web arcade build with no monetization.
- **Target audience:** All ages; casual to mildly competitive players.

---

## 2. Core Loops

- **30-second loop:** Move paddle left/right → ball bounces off paddle → ball travels upward, ricochets off walls and bricks → bricks break and award points → catch ball again on return.
- **Session loop:** Launch ball → survive until level clear or all lives lost → collect power-ups along the way → advance through multiple increasingly difficult levels → post high score.
- **Meta loop:** Beat personal best score → attempt to reach higher levels → master angle control and power-up timing. High score saved to localStorage creates a light long-term goal.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics

1. **Ball physics:** Ball travels at a fixed speed vector; reflects off left/right/top walls and off the top face of the paddle and top/side faces of bricks. Speed increases over time and per level. [Confirmed — classic Breakout]
2. **Paddle angle control:** Where the ball hits the paddle determines its outgoing angle. Paddle divided into zones: center = near-vertical bounce, edges = sharp diagonal bounce. [Confirmed]
3. **Brick destruction:** Ball collides with a brick → brick loses hit points (some bricks take multiple hits) → at 0 HP the brick is destroyed and awards points.
4. **Level clear:** All bricks destroyed → short celebration → next level loads with a new, harder brick layout.
5. **Lives:** Player starts with 3 lives. Ball falls below the bottom edge → life lost → ball respawns on paddle. 0 lives → Game Over.
6. **Ball launch:** Ball sits on paddle before launch; player taps/presses Space or clicks to launch at a fixed upward-angled trajectory.
7. **Power-ups:** Destroyed bricks have a chance to drop a falling power-up capsule. Player must catch it with the paddle.
8. **Paddle shrink (classic rule):** Paddle shrinks after ball penetrates the top wall [Confirmed from original]; in this clone, paddle shrinks per level for difficulty scaling. [Estimated]
9. **Ball speed escalation:** Ball speed increases after a set number of brick hits per level and per level number. [Confirmed]

### Core Verbs
- Move paddle (mouse, keyboard, touch)
- Launch ball
- Catch/deflect ball
- Collect power-up capsule

### Game Modes
| Mode | Description |
|------|-------------|
| **Classic** | Main mode: progress through levels, 3 lives, clear all bricks |
| **Endless (meta)** | Levels loop after completing all defined layouts with increasing difficulty [Estimated] |

*(Only Classic mode is in the MVP; Endless is automatic via level looping.)*

### Input Scheme
| Action | Mouse | Keyboard | Touch |
|--------|-------|----------|-------|
| Move paddle | Mouse X position | Arrow Left / Right | Finger drag X |
| Launch ball | Left click | Space | Tap |
| Pause | — | Escape / P | — |
| Mute | — | M | Tap mute button |

**Orientation:** Landscape or portrait; canvas scales to fit viewport.

### Win / Lose / Fail Conditions
- **Level win:** All bricks cleared → level complete screen → next level auto-loads after 1.5 s.
- **Life lost:** Ball exits bottom edge → lose 1 life → ball respawns on paddle.
- **Game over:** Lives reach 0 → Game Over screen with final score and high score.
- **Continue:** No continues mechanic; restart from level 1. High score is preserved.

### Difficulty Modes
No explicit difficulty selector in MVP. Difficulty scales via level number (see Pacing section).

### Feedback Systems
- **Visual:** Brick flash white on hit, explosion particle burst on destruction, paddle flash on ball catch, screen edge flash on wall bounce.
- **Audio (WebAudio):** Distinct tones for paddle hit, brick hit, brick destroy, power-up collect, life lost, level clear.
- **Haptic:** Not applicable (web; mobile haptic not used).
- **Score pop:** "+N" floating text appears at brick position on destruction.

---

## 4. Progression

### Levels
- 10+ handcrafted brick layouts [Estimated]; after layout 10, repeat with higher speed/fewer bricks-per-row gap.
- Each level increases: ball base speed by ~8%, number of multi-hit bricks, denser layouts with gaps.

### Upgrades / Power-Ups (Transient — last one level or timer)
| Power-Up | Effect | Duration |
|----------|--------|----------|
| Wide Paddle | Paddle width ×1.75 | 15 s |
| Multi-Ball | Spawns 2 extra balls | Until extra balls lost |
| Slow Ball | Ball speed ×0.6 | 10 s |
| Laser Paddle | Paddle fires bullets upward (destroy bricks) | 8 s |
| Fireball | Ball destroys bricks in one hit regardless of HP | 10 s |

### Persistence
- High score saved to `localStorage`. No account/login required.

### Gating
- No hard gates; progression is purely skill-based. Higher levels unlock organically by clearing prior levels in the same session.

---

## 5. Economy & RNG (Tables)

No persistent economy; scoring is the only currency-equivalent.

### Scoring Table [Confirmed for original; values tuned for this clone — Estimated]

| Brick Row (from top) | Color | Points per Brick | HP |
|----------------------|-------|------------------|----|
| 1–2 | Red | 70 | 2 |
| 3–4 | Orange | 50 | 2 |
| 5–6 | Yellow | 30 | 1 |
| 7–8 | Green | 20 | 1 |
| 9–10 | Blue | 10 | 1 |

### Level Score Multiplier [Estimated]
`score_per_brick = base_points × level_multiplier`
where `level_multiplier = 1 + (level - 1) × 0.15` (15% increase per level).

### Power-Up Drop Rate [Estimated]
- Each brick destroyed: 12% chance to drop a random power-up capsule.
- Multi-hit bricks drop on final hit only.

### RNG
- Power-up type: uniform random across 5 types when drop triggers.
- No gacha, no loot boxes.

---

## 6. Content Inventory

### Levels / Layouts: 10 handcrafted [Estimated]
| Level | Theme / Pattern | New Feature Introduced |
|-------|----------------|----------------------|
| 1 | Full grid, all 1-HP | Tutorial-like, slow speed |
| 2 | Checkerboard gaps | First gaps in layout |
| 3 | Diamond pattern | First 2-HP bricks (red) |
| 4 | Fortress (ring) | Dense center, open corners |
| 5 | Diagonal stripes | Mixed HP, faster ball |
| 6 | Pyramid | Tight geometry |
| 7 | Inverted pyramid | First Laser power-up appearance |
| 8 | Cross pattern | Multi-ball likely needed |
| 9 | Dense upper half | Paddle starts narrower |
| 10 | Bonus blitz (all bricks) | Max density, all colors |
| 11+ | Loop from level 1 with +speed modifier | Endless mode via repetition |

### Brick Types: 5 colors × 2 HP tiers = 10 variants [Estimated]
### Power-Up Types: 5 (Wide Paddle, Multi-Ball, Slow Ball, Laser, Fireball)
### Enemy Types: None (bricks are static obstacles, not enemies)
### Characters: None (minimal, abstract theme)

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract arcade space — dark background with neon-lit bricks and a glowing ball. No story or world-building.
- **Premise:** Destroy all bricks; there is no narrative framing. The original Breakout had none; modern clones add minimal "demolish the wall" framing at most.
- **Tone:** Clean, bright, energetic, competitive. Classic arcade aesthetic with modern neon polish.
- **Palette:** Deep dark navy/black background; bricks in red, orange, yellow, green, blue neon; paddle in white/light gray; ball in white with glow; power-up capsules in distinct bright colors.
- **Licensed IP:** None. This is an original-branded clone.
- **Writing style:** Minimal UI text; terse ("LEVEL 3", "GAME OVER", "NEW BEST!", "PAUSED").

---

## 8. Meta & Social Systems

- **High score:** Local best score shown on HUD and Game Over screen.
- **Achievements:** None in MVP.
- **Daily missions / events:** None.
- **Leaderboard:** None (local only).
- **Battle pass / season:** None.
- **Multiplayer:** None.
- **Live-ops burden:** Zero — fully static, self-contained web game.
- **Social sharing:** None in MVP.

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| **Start Screen** | Title, high score, controls list, "Tap / Space to Start" prompt |
| **Gameplay Screen** | Main play canvas with HUD |
| **Pause Screen** | Overlay: "PAUSED", Resume / Restart / Mute buttons |
| **Level Clear Screen** | Brief overlay: "LEVEL X CLEAR!", score tally, auto-advance |
| **Life Lost Screen** | Brief flash, ball respawn on paddle (no full-screen interrupt) |
| **Game Over Screen** | Final score, personal best, "NEW BEST!" if applicable, Restart button |
| **Settings (inline)** | Mute toggle button always visible on HUD (no separate settings screen) |

### Settings / Options
- Mute / unmute audio (M key or on-screen button)
- No other settings in MVP

### Gameplay HUD
| Element | Position |
|---------|----------|
| Score | Top-left |
| Best | Top-center |
| Level | Top-right |
| Lives (hearts/dots) | Below score, top-left |
| Mute button | Top-right corner |
| Active power-up indicator | Bottom strip, shows icon + countdown bar |

### Navigation Flow
```
Start Screen → Gameplay → (ball lost → resume play) → Level Clear → next level
                        → (0 lives) → Game Over → Restart → Start Screen
                        → (Pause) → Pause Overlay → Resume → Gameplay
```

### Onboarding / Tutorial (First-Time User Flow)
1. Start screen shows controls: "Mouse / Arrow keys / Drag to move paddle; Space / Click / Tap to launch."
2. Level 1 begins slow with full brick grid — naturally teaches ball behavior.
3. First power-up drop on level 1 (guaranteed at brick #5) introduces power-up mechanic. [Estimated]
4. No forced tutorial overlay — learn by play.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D**, fixed overhead view (canvas fills viewport).
- No camera movement; play field is the entire canvas.
- **Orientation:** Landscape primary; portrait supported via responsive canvas scaling.

### Art Style
- Minimal neon-on-dark aesthetic.
- Bricks: rounded rectangles with gradient fill and subtle glow.
- Ball: white circle with radial glow, leaves short motion trail.
- Paddle: rounded pill shape, white/cyan with inner glow.
- Background: deep navy, subtle grid scanlines optional.
- Power-up capsules: pill-shaped, color-coded (green=wide, yellow=multi-ball, blue=slow, red=laser, orange=fireball), falling with gentle pulse animation.

### Animation
- Brick destroy: 8-particle burst, 0.3 s fade-out, "+N" score float.
- Ball trail: 5-frame ghost trail at 50% opacity.
- Paddle catch: brief white flash.
- Level clear: screen flash + all remaining bricks explode outward.
- Life lost: screen red flash, ball and paddle reset.

### VFX / Juice
- Screen shake: 4-px shake on life lost.
- Particle burst on each brick destroy.
- Floating score numbers at brick position.
- Power-up capsule pulse glow.
- Ball glow intensity increases with speed.

### Audio (WebAudio generated — no files)
| Sound | Description |
|-------|-------------|
| Paddle hit | Short medium-pitch blip (~200 Hz, 0.05 s) |
| Brick hit (1-HP) | Higher blip (~400 Hz, 0.05 s) |
| Brick destroy | Pop + short decay (~600 Hz, 0.15 s) |
| Wall bounce | Low soft tick (~150 Hz, 0.04 s) |
| Power-up collect | Ascending arpeggio (3 notes, 0.2 s) |
| Life lost | Descending tone (~200→100 Hz, 0.4 s) |
| Level clear | Short fanfare melody (~0.8 s) |
| Game over | Low descending chord (~0.6 s) |

- **Music:** None (keeps file self-contained and avoids royalty concerns).
- **Mute toggle:** Silences all WebAudio output instantly.

---

## 11. Monetization

**None.** This is a pure web arcade game with no ads, IAP, subscriptions, or tracking.
- No ATT prompt needed.
- No GDPR/CMP consent popup needed (no ads, no analytics, no personal data collected).
- No loot boxes or gacha.
- High score stored in localStorage — no PII, no server, no consent required.

---

## 12. Retention Hooks

- **High score persistence:** localStorage best score creates a "beat yourself" loop on every session.
- **Endless levels:** After level 10, game loops with increasing difficulty — no hard stopping point.
- **No ads, no energy system, no lives timer:** Instant restarts remove all friction.
- **Offline / idle earnings:** None (not applicable to arcade action game).
- **Push notifications:** None (static web page).
- **FOMO / urgency:** None (by design — casual, low-pressure).

---

## 13. Localization & Accessibility

### Localization
- Single language: English [Estimated — MVP scope].
- All UI text is minimal and could be replaced trivially.
- No RTL support needed at this stage.

### Accessibility
- **Colorblind:** Bricks are labeled by number/symbol in addition to color [Estimated — simple addition]; high-contrast neon palette is friendly to most colorblind types.
- **Text size:** HUD text ≥ 16 px; scales with canvas.
- **Touch controls:** Full touch support — drag to move, tap to launch.
- **Keyboard controls:** Arrow keys or mouse; Space to launch.
- **Reduced motion:** No explicit reduced-motion flag in MVP; animations are short and not vestibular-risk.
- **Age/content rating:** ESRB E; PEGI 3. No COPPA or GDPR-K concerns — no user data collected.
- **Difficulty assist:** None in MVP; Slow Ball power-up provides natural relief.

---

## 14. Technical Structure

### Engine / Framework
- **Vanilla JavaScript** + **HTML5 Canvas 2D API**.
- No framework, no build step, no external dependencies.
- **Single HTML file** — inline `<style>` and `<script>`.

### Platforms / Offline
- **Web:** Any modern browser (Chrome, Firefox, Safari, Edge).
- **Fully offline:** No network requests; runs by double-clicking the .html file.
- **Mobile web:** Responsive canvas; touch events handled.

### Save System
- `localStorage` key `bb_highscore` stores integer best score.
- No account/auth; no cloud sync.

### Game Loop
- `requestAnimationFrame` loop; delta-time capped to prevent spiral of death on tab switch.
- Target 60 fps.

### Physics
- Ball: AABB collision against bricks, paddle, and walls.
- Sweep/substep to prevent tunneling at high ball speeds (check multiple sub-positions per frame).
- Paddle zones: paddle width divided into 7 zones mapping to outgoing ball angles: -70°, -55°, -40°, -25°, 25°, 40°, 55°, 70° from vertical (leftmost → rightmost).

### Collision Detection
- Brick collision: iterate brick grid; AABB test; determine which face was hit (top/bottom vs left/right) by penetration depth to decide bounce axis.
- Multi-ball: independent physics per ball; any ball can trigger power-up collect.

### Anti-Cheat / Multiplayer
- N/A — single-player, client-only.

### Backend / SDKs / Analytics
- None. Zero external calls.

### App Size
- Target: < 80 KB (single .html file) [Estimated].

---

## 15. Pacing & Difficulty

### Difficulty Curve

| Level | Ball Speed (px/frame @ 60fps) | Paddle Width (% canvas) | Multi-HP bricks % | Notes |
|-------|-------------------------------|------------------------|-------------------|-------|
| 1 | 4.0 | 18% | 0% | Gentle intro |
| 2 | 4.3 | 17% | 10% | First gaps |
| 3 | 4.6 | 16% | 20% | First 2-HP bricks |
| 4 | 5.0 | 16% | 25% | Denser layout |
| 5 | 5.4 | 15% | 30% | Faster, smaller paddle |
| 6 | 5.8 | 14% | 35% | |
| 7 | 6.2 | 14% | 40% | |
| 8 | 6.6 | 13% | 45% | |
| 9 | 7.0 | 13% | 50% | Noticeably hard |
| 10 | 7.5 | 12% | 60% | Endgame |
| 11+ | +0.3/level | 12% cap | 60% cap | Endless scaling |

### Milestone Beats
- Level 1 complete: "Aha" moment — player understands full loop.
- First power-up (multi-ball): delightful chaos, teaches power-ups.
- Level 3 (first 2-HP bricks): requires more hits, raises engagement.
- Level 5: paddle shrink becomes noticeable; skill gap widens here.
- Level 10 clear: achievement moment; game congratulates with special message.

### Churn Points [Estimated]
- Level 3–4: first 2-HP bricks frustrate new players if ball gets stuck in a corner trap.
- Level 6+: fast ball + narrow paddle is where casual players lose all lives quickly and quit.
- Mitigation: Slow Ball and Wide Paddle power-ups appear more frequently on harder levels.

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---------|-----|------|
| Ball + paddle physics | Yes | Yes |
| Brick grid (5 colors, 1–2 HP) | Yes | Yes |
| 3 lives | Yes | Yes |
| 5 power-ups | Yes | Yes |
| 10 level layouts | Yes | Yes |
| Scoring + high score (localStorage) | Yes | Yes |
| Start / Pause / Game Over screens | Yes | Yes |
| WebAudio SFX + mute | Yes | Yes |
| Particle effects | Yes | Yes |
| Mobile touch | Yes | Yes |
| Responsive canvas | Yes | Yes |
| Endless level loop | Yes | Yes |
| Achievements | — | Yes |
| Level select | — | Yes |
| Online leaderboard | — | Yes |
| Multiple save slots | — | Yes |
| Boss bricks (animated) | — | Yes |
| Moving bricks | — | Yes |

### Phased Roadmap

**Phase 1 — Core mechanics (days 1–2)**
- Canvas setup, rAF loop, ball physics, wall bounces.
- Paddle render + mouse/keyboard/touch input.
- Ball-paddle collision with angle zones.
- Ball-wall collision.

**Phase 2 — Bricks & scoring (day 3)**
- Brick grid generation (rows × cols, colors, HP).
- Ball-brick AABB collision + face detection.
- Brick destroy with particle burst + score float.
- Score HUD.

**Phase 3 — Lives & level flow (day 4)**
- Ball-out detection → life lost → respawn.
- Level clear detection → advance.
- Game Over state.
- 10 level layout definitions.

**Phase 4 — Power-ups (day 5)**
- Drop capsule on brick destroy (12% chance).
- 5 power-up types: wide paddle, multi-ball, slow, laser, fireball.
- Power-up HUD indicator + countdown.

**Phase 5 — Polish & audio (day 6)**
- WebAudio SFX for all events.
- Mute toggle.
- Screen shake, trail, glow effects.
- Start / Pause / Game Over screens.
- localStorage high score.
- Mobile responsiveness + viewport meta.

**Phase 6 — QA (day 7)**
- Test tunneling at high speed (substep check).
- Test multi-ball + laser simultaneous.
- Resize / orientation change.
- No console errors.
- Touch on actual mobile device.

### Recommended Tech Stack (Clone)
- HTML5 + Canvas 2D API + Vanilla JS (no dependencies).
- WebAudio API for SFX.
- localStorage for persistence.
- Single .html file delivery.

### Required Asset List
- All art: procedurally drawn on Canvas (no image files).
- All audio: WebAudio generated (no audio files).
- Fonts: system sans-serif or Google Fonts loaded inline (or just system font for offline).
- Data: level layouts as JS arrays.

### Hardest Parts / Risks
1. **Ball tunneling at high speed:** At level 8+ the ball can skip through thin bricks in one frame. Fix: substep collision (check N intermediate positions per frame based on speed).
2. **Multi-ball edge cases:** Multiple balls simultaneously hitting a brick, power-up, and paddle edge. Fix: process each ball independently; mark bricks as destroyed in same-frame iteration.
3. **Paddle zone angle math:** Mapping hit position to outgoing angle consistently; ensure ball always moves upward off paddle (clamp Y velocity to negative).
4. **Mobile touch feel:** Touch events have offset/scale issues on rotated or zoomed viewports. Fix: normalize by canvas bounding rect and devicePixelRatio.
5. **Level layout design:** 10 hand-crafted layouts take time; must be balanced to be clearable. Use a grid of 0/1 values for each layout.

---

## 17. Open Questions

1. **Brick "indestructible" row:** Classic Arkanoid featured silver/gold bricks requiring many hits. Not in MVP; worth considering for level 6+ to add texture. [Estimated to skip for MVP]
2. **Ball stuck detection:** If ball bounces perfectly horizontally it may never hit bricks. A 15-second stuck timer that nudges angle slightly would prevent infinite sessions. [Estimated — easy to add]
3. **Power-up stacking:** Should two Wide Paddle power-ups stack? Define cap (max 2× base width). [Estimated — cap at 1 active per type, refresh timer]
4. **Score multiplier combo:** Should consecutive fast brick breaks award a combo multiplier? Not in MVP, but adds skill expression if added later.
