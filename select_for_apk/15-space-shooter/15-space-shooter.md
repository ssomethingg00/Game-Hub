# Nebula Assault — Clone Blueprint

> Note: This blueprint targets an original-branded clone titled **Nebula Assault**. Replicate mechanics & systems only — not the original Galaga name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Nebula Assault is a fixed-screen vertical shoot-em-up (shmup) where the player pilots a lone fighter ship at the bottom of the screen, moving horizontally and firing upward at waves of alien invaders that sweep into organized formations before launching dive-attack runs. The core tension is managing incoming enemy fire while clearing formations efficiently, chasing high scores, and surviving progressive waves that ramp in speed and aggression. The genre is "fixed-shooter / formation shmup," a direct spiritual successor to the Galaga (1981, Namco) [Confirmed] style.

**Quick facts:** Original-branded clone | Web/HTML5 (Canvas) | Single-player arcade | Rating: Everyone (ESRB equivalent) | No real monetization in base form | Infinite play, high-score persistence | Session: 5–20 min.

---

## 2. Core Loops

- **30-second loop:** Move ship left/right → fire at formation → dodge incoming enemy dive bullets → collect power-up drops → survive until wave clears.
- **Session loop:** Start with 3 lives → survive escalating waves (each wave: formation entry → attack phase → bonus stage every 4th wave) → accumulate score → lose all lives → submit/check high score → restart.
- **Meta loop:** Chase personal best high score (localStorage persistence) → unlock mental mastery of enemy dive patterns → attempt longer survival runs over multiple sessions.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics

| Mechanic | Description |
|---|---|
| Player Movement | Horizontal only; ship stays at bottom band (~85% screen height) [Confirmed] |
| Shooting | Player fires single shots upward; limited to 2 active bullets on screen at once [Confirmed] |
| Enemy Formation Entry | Each wave: enemies fly in along curved/looping paths from screen edges and top, then lock into grid formation [Confirmed] |
| Enemy Formation Attack | Enemies break from formation individually or in groups, dive toward player, fire bullets, then either loop off screen or re-enter formation [Confirmed] |
| Boss Enemy Tractor Beam | Boss-tier enemies descend with a tractor beam; capturing the player's ship costs a life; shooting the boss while it holds the ship frees it and grants dual-ship power-up [Confirmed] |
| Collision | Bullet hits enemy → enemy destroyed + score; enemy/bullet hits player → player loses a life + brief invulnerability period |
| Wave Clear | All enemies destroyed → next wave begins; score bonus for perfect wave clears [Estimated] |
| Challenging Stage | Every 4th wave: enemies fly preset patterns without firing; player scores points per enemy hit, bonus for 100% clear [Confirmed] |
| Power-Ups | Dropped by special enemies: dual-fire (second cannon), rapid-fire, wide-shot [Estimated] |
| Boss Every 5 Waves | A large boss enemy appears every 5 waves, requiring multiple hits, with special attacks [Estimated] |

### Core Verbs
Move (left/right), Shoot (fire upward), Dodge (avoid enemy bullets and diving ships), Collect (power-ups).

### Game Modes
1. **Arcade Mode** — Main mode; infinite waves; score chase; 3 lives; high score tracking. [Confirmed equivalent]
2. **Challenging Stage** — Embedded bonus stages every 4th wave; no enemy fire; timed formation patterns. [Confirmed equivalent]

### Input Scheme
| Action | Keyboard | Mobile |
|---|---|---|
| Move Left | Left Arrow / A | Drag/touch left side of canvas OR virtual D-pad |
| Move Right | Right Arrow / D | Drag/touch right side OR virtual D-pad |
| Fire | Space (hold for auto-fire) | Auto-fire enabled; tap-fire button also shown |
| Pause | Escape / P | Pause button (HUD) |
| Restart | R (on game-over screen) | Restart button shown on game-over screen |

**Orientation:** Portrait (mobile-primary) and landscape (desktop). Canvas scales to fill viewport.

### Win / Lose / Fail States
- **No win condition** — arcade infinite loop. [Confirmed]
- **Lose life:** Enemy bullet or diving enemy body hits player → explosion animation → 2-second respawn → 3-second invulnerability flicker.
- **Game Over:** All lives lost → game-over screen shows final score, wave reached, high score, restart prompt.
- **Wave Complete:** All enemies in wave destroyed → brief "WAVE CLEAR" flash → next wave spawns after 1.5-second gap. [Estimated]
- **Challenging Stage:** All enemies exited/shot → tally screen → resume next regular wave. [Estimated]

### AI / Opponent Behavior
- **Formation entry:** Enemies enter via pre-scripted curved paths (sinusoidal, looping arcs) [Confirmed]; path speeds increase per wave.
- **Attack dives:** Individual enemies or pairs break from formation on a timer; dive angle tracks player's last known X position; speed accelerates with wave number. [Confirmed]
- **Bullet firing:** Enemies fire downward during dives at semi-randomized intervals; bullet count/speed scale with wave. [Confirmed]
- **Boss Galaga equivalent:** Loops wide, fires multiple bullets, deploys tractor beam if player is in alignment range. [Confirmed equivalent]

### Difficulty Modes
None built in — difficulty is purely wave-based progression. [Confirmed equivalent for Galaga-style]

### Feedback Systems
- Visual: hit flash on enemies, explosion particle burst, screen-edge glow on player hit, score pop-up at hit location.
- Audio: shoot SFX, explosion SFX, power-up chime, enemy dive warning sound, boss music sting, game-over jingle (all WebAudio synthesized).
- Haptic: not applicable for web.

---

## 4. Progression

### Wave Structure
| Wave Range | Enemy Count | Dive Speed | Bullets per Enemy | Special |
|---|---|---|---|---|
| 1–3 | 24 | 1.0× | 1 | Intro only |
| 4 | Challenging Stage | — | None | Bonus score |
| 5–8 | 30 | 1.2× | 1 | Boss Galaga appears |
| 8 | Challenging Stage | — | None | Bonus score |
| 9–15 | 36 | 1.5× | 2 | Boss fires beam |
| 16–20 | 40 | 1.8× | 2 | Fast-entry paths |
| 21+ | 40 | 2.0×+ | 3 | Full aggression |

[All values Estimated based on Galaga genre norms]

### Unlock / Upgrade Systems
- No persistent upgrades between runs — arcade roguelike-style, within-run power-ups only. [Confirmed equivalent]
- **Dual Ship:** Freeing a captured ship grants double-width firing for the remainder of the life. [Confirmed equivalent]
- **Power-up drops (in-run):** Rapid-fire (faster bullet rate), Wide-shot (bullet fans), Shield pulse (absorbs 1 hit). [Estimated]

### Prestige / Rebirth
None — pure arcade high-score format. [Confirmed]

### Gating
- No paywalls; progression gating is purely survival-skill based.
- New enemy types introduced at wave 5 (Boss), wave 9 (Beam), wave 16 (Fast), wave 21 (Swarm). [Estimated]

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Score Points | Soft (in-run only) | Shooting enemies, challenging stages, wave bonuses | Not spent — pure score display |
| High Score | Persistent soft | Beating previous best | Bragging rights / leaderboard |

No real economy — no premium currency, no IAP in base game. [Confirmed equivalent]

### Score Values
| Enemy Type | In Formation | Diving |
|---|---|---|
| Drone (Bee equivalent) | 100 pts | 200 pts |
| Fighter (Butterfly equivalent) | 150 pts | 300 pts |
| Commander (Boss Galaga equivalent) | 400 pts | 800 pts |
| Mega Boss (every 5 waves) | 2,000 pts | — |
| Challenging Stage enemy | 100 pts | — |
| Challenging Stage 100% clear bonus | +5,000 pts | — |
| Wave clear bonus | +500 × wave# pts | — |

[Values Estimated, tuned to genre norms; Galaga originals: Bee 50/100, Butterfly 80/160, Boss 150/400]

### Extra Lives
- Earn extra life at 20,000 pts, then every 50,000 pts thereafter. [Estimated based on Galaga: 20K / 70K Confirmed]

### RNG / Drop Rates
| Event | Probability |
|---|---|
| Enemy drops power-up on death (Fighter+ only) | 8% per eligible enemy [Estimated] |
| Power-up type (Rapid-fire) | 50% of drops [Estimated] |
| Power-up type (Wide-shot) | 30% of drops [Estimated] |
| Power-up type (Shield pulse) | 20% of drops [Estimated] |
| Boss dive pattern: tractor beam | 40% chance on any dive past wave 5 [Estimated] |
| Enemy dive triggers: per-tick chance | 0.3% per enemy per frame at wave 1; +0.05% per wave [Estimated] |

### Cost Scaling
No upgrade purchase system — N/A. Difficulty scaling formula: `speed_multiplier = 1.0 + (wave - 1) * 0.08` [Estimated].

---

## 6. Content Inventory *(counts + lists)*

### Waves / Stages
- Infinite waves; repeating pattern of 4 combat waves + 1 challenging stage [Confirmed equivalent].
- Every 5 waves introduces a new enemy behavior tier. [Estimated]
- Practical play ends around wave 30–50 for most players. [Estimated]

### Enemy Types
| Name | Color | HP | Special Behavior |
|---|---|---|---|
| Drone | Cyan | 1 | Basic dive, 1 bullet |
| Fighter | Magenta | 1 | Erratic weave dive, 1 bullet |
| Commander | Gold | 2 | Wide dive arc, tractor beam, 2 bullets |
| Swarm Scout | Red | 1 | Fast dive, appears wave 16+ |
| Mega Boss | Purple | 10 | Screen-wide, fires spread bursts, phase changes at 50% HP |

### Player Ships
- **Nebula Fighter** — default ship (triangle + thruster glow).
- **Dual Nebula Fighter** — captured ship freed; two fighters side-by-side, double fire rate. [Confirmed equivalent]

### Power-Ups (collectible drops)
1. Rapid-Fire — increases fire rate 2× for 15 seconds.
2. Wide-Shot — fires 3 bullets in spread for 15 seconds.
3. Shield Pulse — absorbs next 1 hit; visible aura on ship.
4. Score Multiplier ×2 — doubles points for 10 seconds. [Estimated]

### Projectiles
- Player bullet: narrow vertical cyan beam.
- Enemy standard bullet: small red oval, medium speed.
- Boss spread bullet: orange diagonal shots (3-way fan).
- Tractor beam: sustained green vertical column.

### Particle Effects
- Enemy explosion: 8–12 colored fragments radiate outward.
- Player hit explosion: blue-white burst.
- Power-up pickup: expanding ring flash.
- Challenging stage clear: confetti-style burst.

---

## 7. Theme, Narrative & Tone

**Setting:** Deep space, year 2147. The Nebula Armada — a hive-mind alien swarm from the Cygnus Nebula — has begun its first incursion into the solar system. Earth's last line of defense is a single experimental fighter: the Nebula Assault craft. [Estimated original lore]

**Premise delivery:** Title screen flavor text only; no cutscenes. Pure arcade — story is backdrop, not driver. [Confirmed equivalent for genre]

**Tone:** Classic arcade — tense but fun, fast-paced, mildly retro-futuristic. Bright neon on dark space. No gore — enemies explode into colorful particles. Family-friendly.

**Visual Theme:** Deep black/navy space, pixel-art-inspired vector shapes, neon glow effects (cyan, magenta, gold, purple), star-field background with parallax scrolling.

**Character Personalities:** None — pure arcade abstraction. [Confirmed equivalent]

**Licensed IP:** None — fully original. [Confirmed]

---

## 8. Meta & Social Systems

**Daily Rewards:** None in base arcade format. [Estimated — optional add-on]

**Achievements:** Wave milestones (Reach Wave 10, Wave 20, etc.), score milestones, perfect wave clears, challenging stage clears. [Estimated — shown on game-over screen]

**Leaderboard:** Local high score (localStorage). Online leaderboard optional future feature. [Estimated]

**Live-Ops Cadence:** None — static arcade game. No ongoing content burden.

**Multiplayer:** None in base game. [Confirmed equivalent — original Galaga is single-player]

**Sharing:** Score share text generation (copy to clipboard) on game-over screen. [Estimated]

**Events / Battle Pass:** None. [Confirmed equivalent]

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| **Title / Start** | Game logo, "TAP TO START", controls summary, high score display |
| **Main Game** | Active gameplay with HUD overlay |
| **Pause Screen** | Overlay: PAUSED, Resume, Restart, Mute toggle |
| **Wave Clear** | Brief flash overlay: "WAVE [N] CLEARED" + score gained |
| **Challenging Stage Intro** | "CHALLENGING STAGE" banner with enemy preview |
| **Challenging Stage Tally** | "HIT COUNT: X/40" + bonus score earned |
| **Game Over** | Final score, wave reached, high score, restart button |
| **Extra Life Toast** | Pop-up toast: "+1 LIFE" when score threshold hit |

### Settings / Options (minimal)
- Mute toggle (SFX + music) — accessible from pause screen and HUD corner.
- No accounts, no cloud save, no language settings in base version.

### HUD Elements (during gameplay)
- Score (top-left)
- High Score (top-center)
- Wave number (top-right)
- Lives (bottom-left, ship icons)
- Active power-up + timer bar (bottom-right)
- Mute button (top-right corner)
- Pause button (top-right, desktop; bottom-right, mobile)

### Navigation Flow
```
Title Screen
    ↓ [Tap/Space]
Main Game ←→ Pause Screen
    ↓ [wave clear]
Wave Clear Overlay
    ↓ [auto]
Main Game (next wave) OR Challenging Stage Intro
    ↓
Challenging Stage Tally
    ↓
Main Game (next wave)
    ↓ [all lives lost]
Game Over Screen
    ↓ [restart]
Title Screen / Main Game (new run)
```

### Onboarding / Tutorial (first run)
1. Title screen shows control diagram: arrow keys (desktop) + drag to move (mobile); spacebar / auto-fire.
2. Wave 1 starts with only Drone-type enemies; no enemy fire for first 5 seconds [Estimated grace period].
3. First power-up guaranteed drop on wave 1 to teach the mechanic. [Estimated]
4. First Commander enemy telegraphs tractor beam with 1.5s warning sound before activating. [Estimated]
5. No text-pop-up tutorial — pure "observe and learn" arcade style.

---

## 10. Art, Audio, Camera & Feel

**Dimension:** 2D flat [Confirmed equivalent]
**Camera:** Fixed top-down (player at bottom, enemies above); no scrolling X or Y [Confirmed equivalent]
**Orientation:** Portrait-primary (mobile); canvas letterboxed on landscape/desktop.
**Aspect Ratio:** 9:16 base (360×640 internal resolution, scaled up) [Estimated]

### Art Style
- Vector/canvas-drawn shapes — no image files required.
- Player ship: white/cyan triangle with blue thruster flame.
- Enemy Drone: cyan hexagon outline.
- Enemy Fighter: magenta diamond with wing spikes.
- Enemy Commander: gold/amber double-ring shape.
- Mega Boss: large purple polygon, multi-segmented.
- Bullets: glowing rectangles (player: cyan; enemy: red/orange).
- Background: procedural starfield — 3 layers of white dots at different speeds (parallax).
- Neon glow effect: canvas shadow blur on all ships and bullets.

### Color Palette
| Role | Color |
|---|---|
| Background | #000010 (near-black navy) |
| Stars | #ffffff, #aaccff, #8899ff |
| Player ship | #00ffff (cyan) + #ffffff |
| Drone | #00e5ff |
| Fighter | #ff00aa (hot magenta) |
| Commander | #ffcc00 (gold) |
| Mega Boss | #cc44ff (purple) |
| Player bullets | #00ffff |
| Enemy bullets | #ff4444 |
| Tractor beam | #44ff88 (green) |
| HUD text | #ffffff |
| Score flash | #ffff00 |

### Animation
- Ships: no frame animation — shape transforms (scale pulse on formation lock).
- Thrust flame: oscillating triangle height on player ship.
- Enemy entry paths: scripted bezier-curve motion.
- Explosions: particle system (8–12 fragments, physics velocity + fade).
- Formation idle: gentle sine-wave bob (entire formation oscillates left-right). [Confirmed equivalent]

### VFX / Juice
- Screen flash (brief white overlay at 20% opacity) on player hit.
- Score pop-up number at enemy hit position, floats upward and fades.
- Tractor beam: animated green column with pulsing width.
- Power-up: rotating icon with glow ring, disappears in burst on collect.
- Formation entry: enemies trail a faint motion blur.

### Audio (WebAudio API — no external files)
| Sound | Generation Method |
|---|---|
| Player shoot | Short square-wave blip (freq: 800Hz, dur: 0.08s, fast decay) |
| Enemy explode | Noise burst + downward freq sweep |
| Player hit | Low thud + noise burst |
| Power-up collect | Short ascending arpeggio (3 notes) |
| Boss tractor beam | Sustained sine wave with tremolo |
| Game over | Descending chord sequence |
| Wave clear | Ascending 3-note fanfare |
| Challenging stage | Upbeat short jingle |
| Background music | Looping procedural beat (bass note + rhythmic pulse) [Estimated — optional] |

---

## 11. Monetization

**Base game:** No monetization — pure arcade HTML5 game. [Confirmed for this implementation]

**Future monetization paths (for reference only):**

| Type | Placement | Notes |
|---|---|---|
| Rewarded Video Ad | Game Over screen: "Watch ad for +1 life" | Most natural placement |
| Banner Ad | Bottom of game canvas | Low-friction |
| Interstitial | Between waves (every 5th wave) | High revenue, intrusive |
| IAP — Remove Ads | One-time purchase $1.99 | If ads added |
| IAP — Starter Pack | Ship skins + score booster $0.99 | Optional |

**ATT / GDPR / Consent:** If ads are added, implement ATT prompt (iOS) and GDPR CMP popup before ad SDK init. For this base version: N/A.

**Loot boxes / gacha:** None. [Confirmed]

**Aggressiveness:** None in base version — pure gameplay focus. [Confirmed]

---

## 12. Retention Hooks

**Daily rewards:** Not implemented in base arcade version. [Estimated — optional]

**Idle / offline earnings:** None — active arcade game only. [Confirmed]

**High score persistence:** localStorage saves best score; displayed on title screen and game-over screen to motivate re-play. [Confirmed for this implementation]

**Push notifications:** None in browser-based version. [Confirmed]

**FOMO / urgency:** Challenging stage bonus (you miss points if you don't shoot accurately) creates urgency. Extra life milestones create natural "one more run" motivation. [Estimated]

**Lives system:** 3 lives per run; extra lives earned at score thresholds; no wait timer (not a mobile freemium game). [Confirmed equivalent]

**Session-end hooks:**
- Game-over screen shows wave reached, encouraging "can I beat wave X?" thinking.
- High score comparison always visible.

---

## 13. Localization & Accessibility

**Languages:** English only in base implementation. [Estimated]

**RTL support:** None required for base version. [Estimated]

**Text scaling:** Canvas-rendered text scales with canvas size; minimum 14px equivalent at smallest viewport. [Estimated]

**Colorblind considerations:** Enemy types differentiated by shape (hexagon/diamond/ring/polygon) in addition to color, ensuring shape-based identification for colorblind players. [Estimated]

**Difficulty / assist options:** None explicit; wave 1 grace period serves as gentle difficulty ramp. [Estimated]

**Subtitle / caption options:** No dialogue — N/A. [Confirmed]

**Age / content rating:** ESRB: Everyone (E) — no violence beyond colorful explosions, no language, no COPPA concerns (no data collection, no accounts). GDPR: minimal — localStorage only, no PII. [Estimated]

**Regional differences:** None — no localized pricing or content restrictions in base version. [Confirmed]

---

## 14. Technical Structure

**Engine / Framework:** Vanilla JavaScript, HTML5 Canvas 2D API — no framework or build step. [Confirmed for this implementation]

**Language:** JavaScript (ES6+)

**Platform:** Web browser (Chrome, Firefox, Safari, Edge); runs offline by double-click (single .html file). [Confirmed]

**Online / Offline:** Fully offline. No network requests. [Confirmed]

**Save System:** localStorage for high score only. Key: `nebula_assault_hiscore`. No accounts, no cloud save. [Confirmed]

**Cross-device sync:** None — local only. [Confirmed]

**Multiplayer / Netcode:** None. [Confirmed — single-player]

**Anti-Cheat:** N/A — single-player local game. [Confirmed]

**Backend Services:** None. [Confirmed]

**Analytics:** None in base version. [Confirmed]

**Third-party SDKs:** None. [Confirmed]

**Performance notes:**
- Object pooling for bullets (player + enemy) and particles.
- Enemy array cleaned on death; no memory leaks.
- requestAnimationFrame loop; delta-time movement for frame-rate independence.
- Canvas resize handler with debounce for responsive scaling.
- Target 60fps on mid-range mobile (< 200 active objects at once).

**File size:** Single .html file, estimated 25–50KB. [Estimated]

**Browser requirements:** Any modern browser with Canvas and WebAudio support (2018+). [Estimated]

---

## 15. Pacing & Difficulty

### Early Game (Waves 1–4)
- Only Drone enemies; slow entry paths; no enemy fire for first 3 seconds per wave.
- Forgiving — player learns movement and shooting basics.
- First challenging stage at wave 4 feels like a reward.
- **"Aha" moment:** Shooting an enemy that drops a power-up for the first time.

### Mid Game (Waves 5–15)
- Fighter enemies introduced with erratic dive paths.
- Commander Boss enemies begin tractor beam attacks.
- Enemy bullet count increases; player must actively dodge.
- Dual-ship mechanic available if boss capture occurs.
- **"Aha" moment:** Successfully shooting free a captured ship; experiencing double firepower.
- Churn point: Players who don't learn to dodge the tractor beam die repeatedly here. [Estimated based on Galaga community feedback]

### Late Game (Waves 16–30+)
- Swarm Scouts added — fast, unpredictable dives.
- Multiple simultaneous dives; enemy bullets are faster.
- Mega Boss every 5 waves; requires sustained accurate fire.
- Formation entries speed up dramatically.
- **"Aha" moment:** Clearing a full wave with zero hits for large score bonus.
- Churn point: Wave 20+ requires near-perfect play; casual players will exit here. [Estimated]

### Difficulty Scaling Formula
```
wave_speed = 1.0 + (wave_number - 1) * 0.08       // speed multiplier
enemy_fire_rate = 0.3 + (wave_number - 1) * 0.05  // base fire chance per frame (%)
bullets_per_salvo = 1 + floor((wave_number - 1) / 8) // max bullets per enemy dive
```
[All Estimated]

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

**MVP (Phase 1 — playable core)**
- [x] Canvas setup, responsive scaling, starfield background
- [x] Player ship: move left/right, fire bullets
- [x] Enemy grid formation (4 rows × 8 columns = 32 enemies)
- [x] Formation entry animation (scripted paths)
- [x] Enemy dive attacks (2–3 enemies at once)
- [x] Basic enemy fire
- [x] Bullet/enemy/player collision detection
- [x] Score, lives, wave counter HUD
- [x] Wave clear + next wave spawn
- [x] Game over screen + restart
- [x] High score localStorage persistence
- [x] Keyboard controls (arrows + space)
- [x] Mobile touch controls (drag + auto-fire)

**Full Feature (Phase 2 — polish)**
- [ ] All 5 enemy types with distinct behaviors
- [ ] Power-up drops (3 types) with timers
- [ ] Commander tractor beam mechanic + dual ship
- [ ] Challenging stages every 4th wave
- [ ] Mega Boss every 5 waves (multi-phase)
- [ ] WebAudio SFX (shoot, explode, power-up, game-over)
- [ ] Particle explosion system
- [ ] Score pop-ups at hit locations
- [ ] Extra life at score thresholds
- [ ] Pause screen
- [ ] Mute toggle
- [ ] Wave-difficulty scaling (speed + fire rate)
- [ ] Formation idle animation (sine bob)
- [ ] Screen flash on player hit

### Phased Roadmap

**Phase 1 — Core Loop (2–4 hours)**
1. HTML canvas setup, viewport meta, responsive scaling.
2. Game state machine (title, playing, paused, game-over).
3. Player ship class: render, move, shoot, collision bounds.
4. Bullet class + pool (max 10 player bullets).
5. Enemy class + grid layout (4×8); placeholder shapes.
6. Wave spawn: enemies enter via linear paths first.
7. Collision detection: bullet↔enemy, enemy/bullet↔player.
8. Score + lives + wave HUD.
9. Wave clear → new wave. Game over screen. Restart.
10. Keyboard controls.

**Phase 2 — Enemies & Waves (2–3 hours)**
1. Enemy type system: Drone, Fighter, Commander, Scout, Mega Boss.
2. Bezier/sinusoidal entry path system.
3. Dive attack AI per enemy type.
4. Enemy bullet pool + firing logic.
5. Wave difficulty scaling.
6. Challenging stage logic (wave 4, 8, 12...).
7. Mega Boss spawn + multi-hit + phases.

**Phase 3 — Power-Ups & Feel (1–2 hours)**
1. Power-up item class + drop logic + RNG.
2. Rapid-fire, wide-shot, shield-pulse effects + timers.
3. Tractor beam: Commander mechanic + dual ship logic.
4. Particle explosion system.
5. Score pop-ups at hit position.
6. Extra life at score threshold.
7. Formation idle sine-bob animation.

**Phase 4 — Audio & UI Polish (1–2 hours)**
1. WebAudio context + all SFX synthesis.
2. Background music loop (optional).
3. Mute toggle.
4. Screen flash on player hit.
5. Start screen with controls diagram.
6. Pause overlay.
7. Wave clear / challenging stage banner overlays.
8. Mobile touch drag controls + auto-fire.

**Phase 5 — QA & Ship (30–60 min)**
1. Object pool audit — no leaks.
2. Resize / orientation test.
3. Mobile touch test.
4. Restart full state reset verification.
5. localStorage high score test.
6. Console error sweep.
7. File completeness check (ends with `</html>`).

### Recommended Tech Stack (for clone)
- **Language:** Vanilla JavaScript (ES6+)
- **Rendering:** HTML5 Canvas 2D
- **Audio:** Web Audio API (no external files)
- **Storage:** localStorage
- **Build:** None — single .html file
- **Testing:** Browser DevTools, mobile emulator

### Required Asset List
All assets are procedurally generated (drawn with Canvas 2D API):
- Player ship shape (triangle + fins)
- Enemy shapes (hexagon, diamond, ring, polygon, boss polygon)
- Bullet rectangles (player + enemy variants)
- Particle fragments (small squares/circles)
- HUD font (system font: "Courier New" or "monospace")
- Star dots (random scatter at init)

No image files, no audio files — 100% code-generated.

### Hardest Parts / Risks
1. **Formation entry path system** — Bezier curve paths for each enemy to reach its grid slot without collision. Risk: enemies overshooting or colliding. Mitigation: pre-calculate waypoints per slot; use lerp-to-target with arrival dampening.
2. **Tractor beam mechanic** — Detecting beam alignment with player position; animating ship capture and storage in formation; freeing dual ship. Risk: state machine complexity. Mitigation: explicit capture states (free, captured, being-towed, towing).
3. **Mobile touch feel** — Responsive drag-to-move that feels precise at 60fps on low-end Android. Risk: laggy or jumpy movement. Mitigation: use `touchmove` events with direct position mapping (not delta-based).
4. **Responsive canvas scaling** — Canvas must remain crisp at all screen sizes (no blur). Risk: devicePixelRatio scaling on retina. Mitigation: explicit DPR scaling in canvas setup.
5. **State reset on restart** — All arrays, pools, timers, and state flags must reset cleanly. Risk: ghost enemies or stale state. Mitigation: centralized `resetGame()` function.

---

## 17. Open Questions

1. **Exact Galaga dual-ship recapture mechanic** — Whether to implement the capture/rescue mechanic at full fidelity or simplify to a power-up drop. Recommend simplifying for scope. [Would need design decision]
2. **Background music generation** — How much procedural complexity to implement; a simple looping bass/pulse or a fuller chiptune-style composition. Recommend starting minimal.
3. **Challenging stage enemy counts** — Exact count and pattern variety beyond the base "40 enemies fly a preset path" format. [Can be tuned in testing]
4. **Mega Boss health threshold** — Whether 10 HP is the right balance for wave-5 difficulty; needs playtesting to confirm feel.
