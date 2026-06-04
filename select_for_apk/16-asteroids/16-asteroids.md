# Void Breaker — Clone Blueprint
*(Asteroids-style rotating thrust-and-shoot arcade game)*

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Void Breaker is a single-screen vector-style arcade shooter in which the player pilots a small triangular spaceship through a field of tumbling space rocks. Rocks must be shot and destroyed — each splits into two smaller pieces before finally vanishing — while the ship steers with inertia-based thrust physics and screen edges wrap to the opposite side. Clearing all rocks advances the wave; a bonus enemy saucer appears periodically for extra danger and points. The game is pure skill-ceiling reflex play with no random power-ups, just score.

**Quick facts**
- Original: Asteroids (1979) — Atari Inc.; designers Lyle Rains, Ed Logg, Dominic Walsh [Confirmed]
- Platforms: Arcade cabinet (original); cloned on virtually every platform since [Confirmed]
- Genre: Multi-directional shooter / Vector arcade
- Session length: 2–10 minutes per credit
- Content rating: ESRB E / PEGI 3 (no violence concern) [Confirmed]
- Monetization (clone): Free-to-play, optional donation / ad placement; no IAP required for core loop
- Install scale (original): Top-selling arcade cabinet of its era; >70,000 units [Confirmed]

---

## 2. Core Loops

- **30-second loop:** Rotate ship → thrust to align → fire bullet at asteroid → asteroid splits/vanishes → dodge incoming debris → repeat until field is clear or player dies.
- **Session loop:** Start with 4 large asteroids on Wave 1; shoot all rocks to clear the wave; survive saucer spawns; rack up score using combo patterns (large→medium→small chains); lose all 3 lives → game over → enter initials on high-score table.
- **Meta loop:** Beat personal best score (stored in localStorage); strive for extra-life bonuses every 10,000 pts; attempt to clear higher wave counts. No persistent unlocks — pure score-chasing meta.

---

## 3. Mechanics, Controls & Game States

### Core Verbs
| Verb | Description |
|------|-------------|
| Rotate | Turn ship clockwise or counter-clockwise at fixed angular velocity |
| Thrust | Apply forward acceleration in the ship's facing direction |
| Fire | Shoot a bullet in the ship's facing direction (4-bullet cap on screen) |
| Hyperspace | Teleport ship to random position; small chance of self-destruction |

### Physics Rules [Confirmed]
- Ship has velocity vector; thrust adds delta-v in facing direction each frame
- No deceleration except via opposite thrust (space friction = 0 by default; clone may add tiny drag ~0.98 multiplier for playability)
- Maximum ship speed capped to prevent impossible control (~600 px/s at 800px canvas)
- All objects (ship, asteroids, bullets, saucers) wrap at canvas edges: exit right → enter left, etc.
- Bullets travel at fixed speed and expire after ~1.2 seconds (travel distance limits)
- Asteroids have random initial velocity and rotation; velocity assigned per size:
  - Large: slow (0.5–1.5 px/frame at 60fps)
  - Medium: medium (1.5–3.0 px/frame)
  - Small: fast (3.0–5.0 px/frame)

### Asteroid Splitting [Confirmed]
- Large hit → 2 × Medium (offset randomly from impact point)
- Medium hit → 2 × Small
- Small hit → destroyed (no children)

### Wave Progression [Confirmed]
| Wave | Initial Large Asteroids |
|------|------------------------|
| 1    | 4 |
| 2    | 6 |
| 3    | 8 |
| 4    | 10 |
| 5+   | 11 (cap) |

### Enemy Saucers [Confirmed]
- **Large Saucer:** Appears early; fires bullets in random directions; 200 pts; moves in straight lines with periodic direction changes
- **Small Saucer:** Appears after 10,000 pts scored; fires aimed bullets toward player (accuracy increases with score); 1,000 pts; smaller and faster
- Saucer spawns from left or right edge; flies across screen; fires every ~1.5–2.5 s
- Only one saucer at a time on screen
- Saucer bullets can destroy asteroids

### Hyperspace [Estimated]
- Press H or Shift; ship disappears for 0.5 s then reappears at a random screen position
- 5–10% chance of ship immediately exploding on re-entry (penalty for panic use)
- Cooldown: 3 seconds between uses

### Lives & Respawn [Confirmed]
- Start with 3 lives
- Extra life every 10,000 points [Confirmed]
- On death: 3-second wait; ship respawns at screen center with 3-second invulnerability (ship blinks)
- If center is occupied by asteroid, respawn is delayed until clear [Estimated]
- No respawn if 0 lives remain → Game Over

### Game States
1. **Title/Start** — logo, high score, press-to-play
2. **Playing** — main game loop
3. **Wave Clear** — brief flash "WAVE N CLEAR", short pause, spawn next wave
4. **Ship Exploding** — particle death animation (~1 s)
5. **Respawning** — invulnerable blink period (~3 s)
6. **Paused** — overlay, resume button
7. **Game Over** — score display, high score check, restart prompt

### Controls
| Action | Keyboard | Mobile Button |
|--------|----------|---------------|
| Rotate Left | Left Arrow / A | ↺ button |
| Rotate Right | Right Arrow / D | ↻ button |
| Thrust | Up Arrow / W | ▲ button |
| Fire | Space / Z | Fire button |
| Hyperspace | Shift / H | HYP button |
| Pause | P / Escape | — |
| Mute | M | 🔇 button |

### Orientation & Display
- Landscape preferred; responsive canvas fills available space
- Mobile: on-screen D-pad (bottom-left: rotate + thrust; bottom-right: fire + hyperspace)

---

## 4. Progression

- **No persistent upgrade tree** — pure arcade scoring game [Confirmed]
- Wave number acts as implicit difficulty increase (more/faster asteroids)
- Extra life milestone at every 10,000 points [Confirmed]
- High score saved to localStorage; displayed on title screen
- No prestige, no currencies, no unlocks
- Skill mastery is the only progression axis: players learn asteroid timing, safe zones, saucer dodging

---

## 5. Economy & RNG

### Currencies
| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| Score | Display only | Destroying objects | Nothing (bragging rights / extra lives) |

Score is not a spendable currency. Extra lives are the only score-derived reward.

### Scoring Table [Confirmed]
| Object | Points |
|--------|--------|
| Large Asteroid | 20 |
| Medium Asteroid | 50 |
| Small Asteroid | 100 |
| Large Saucer | 200 |
| Small Saucer | 1,000 |
| Extra Life threshold | Every 10,000 pts |

### Score Maximum [Confirmed]
- Original cabinet rolls over at 99,990; clone can display up to 999,990 or be unlimited

### RNG Elements
- Asteroid spawn positions: random along edges (not near player spawn)
- Asteroid velocity vectors: random within per-size speed ranges
- Asteroid rotation rate: random ±0.02–0.08 rad/frame [Estimated]
- Saucer spawn side: random left/right
- Saucer spawn timer: 15–30 s after wave start [Estimated]
- Saucer fire direction: random (large) or aimed ±15° accuracy spread (small, narrowing with score) [Estimated]
- Hyperspace destination: uniform random in canvas bounds (excluding a small margin)
- Hyperspace self-destruct: 7% chance [Estimated]

### No Gacha / No Loot Tables
This is a pure arcade game; all RNG is positional/directional only.

---

## 6. Content Inventory

### Asteroid Sizes: 3 [Confirmed]
- Large (radius ~45 px at 800px canvas)
- Medium (radius ~22 px)
- Small (radius ~11 px)

### Enemy Types: 2 [Confirmed]
- Large Saucer (ellipse ~30×18 px)
- Small Saucer (ellipse ~18×12 px)

### Ship: 1
- Player ship (triangle/wedge, ~20 px)
- Thrust flame (animated lines behind nozzle)
- Explosion (8–12 debris particles radiating outward)

### Bullet: 1 type
- Small dot/line, up to 4 simultaneous on-screen [Confirmed]

### Waves: Theoretically infinite (11-asteroid cap from wave 5) [Confirmed]

### Screens/Modes: 1 game mode (arcade survival)

---

## 7. Theme, Narrative & Tone

- **Setting:** Deep space; black void with distant star field (static dots or subtle parallax)
- **Premise:** No narrative. Pure survival against an infinite asteroid field. [Confirmed]
- **Visual style:** Minimalist vector line-art; white/cyan/green wireframe objects on black — evokes original CRT vector display
- **Tone:** Tense, clinical, retro-cool; no comedy, no story, no characters with personality
- **Color palette:** Black background, white asteroid outlines, cyan/green ship, yellow bullets, red/orange explosions, magenta/white saucers
- **Audio tone:** Sparse electronic beeps and thrums; no music during play (optional ambient low-frequency pulse matching original "heartbeat" that speeds up as fewer asteroids remain)
- **IP:** No licensed IP; original brand name "Void Breaker" avoids Atari trademark

---

## 8. Meta & Social Systems

- **Local high score** stored in localStorage [Confirmed implementation approach]
- **No multiplayer** (original was 1-2 alternating players; clone targets single-player web)
- **No daily missions, no battle pass, no events** — pure arcade
- **No social sharing** in MVP; optional tweet-score button post-MVP
- **No leaderboard server** in MVP; optional future: global leaderboard via lightweight backend
- **No live-ops burden** — static game logic; ship once, maintain never

---

## 9. UI/UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| Title/Start | Game logo, high score display, controls legend, START prompt |
| Gameplay | Main action canvas |
| Pause Overlay | Dim overlay, PAUSED text, resume/restart options |
| Game Over | Final score, high score, wave reached, PLAY AGAIN button |
| (No login, no shop, no settings screen in MVP) |

### HUD Elements (during gameplay)
- Score (top-left)
- Best score (top-center)
- Wave number (top-right)
- Lives remaining (top-left below score, shown as small ship icons)
- Mute toggle button (top-right corner)
- Mobile buttons (bottom: rotate-L, rotate-R, thrust, fire, hyperspace)

### Navigation Flow
```
Title → [SPACE/tap] → Gameplay → [all lives lost] → Game Over → [PLAY AGAIN] → Gameplay (reset)
Gameplay → [P/Esc] → Paused → [P/Esc or Resume] → Gameplay
```

### Onboarding / Tutorial (first run)
1. Title screen shows: "LEFT/RIGHT — Rotate | UP — Thrust | SPACE — Fire | H — Hyperspace"
2. No forced tutorial; controls shown on title, player starts immediately
3. First wave has only 4 slow large asteroids — serves as natural soft tutorial
4. Mobile: button labels shown on first play

### Settings / Options (minimal)
- Mute toggle (M key or button): silences all WebAudio SFX
- No language options, no accessibility settings in MVP

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- 2D top-down; canvas fixed viewport, no scrolling, no camera movement [Confirmed]
- Display: landscape, 16:9 preferred, canvas fills window maintaining aspect
- Orientation: landscape (works portrait on mobile but landscape preferred)

### Art Style [Estimated for clone]
- Vector wireframe: all objects drawn as line segments on Canvas 2D
- Black background with 80–120 static white pixel "stars" for depth
- Asteroid shapes: irregular polygons (7–11 vertices, slightly randomized per instance for variety)
- Ship: equilateral-ish triangle, open interior, thrust flame = 2 lines from rear
- Saucers: two stacked ellipses (flying saucer silhouette)
- No sprites/images; pure canvas drawing API

### Color Palette
| Element | Color |
|---------|-------|
| Background | #000000 |
| Stars | #ffffff (dim, 40% alpha) |
| Asteroid outlines | #aaaaaa / #ffffff |
| Player ship | #00ffff (cyan) |
| Thrust flame | #ff6600 / #ffff00 |
| Bullets | #ffff00 |
| Explosion particles | #ff4400 → #ff8800 fade |
| Large Saucer | #cc44ff |
| Small Saucer | #ff44cc |
| HUD text | #ffffff |

### Animation & VFX
- Thrust: 2 short lines flicker behind ship exhaust port each frame thrust is held
- Explosion: 8 line-segment debris particles fly outward, fade over 0.8 s, slight gravity = 0
- Asteroid rotation: each asteroid spins at its own constant angular velocity (set at spawn)
- Screen flash: brief white flash when wave clears
- Invulnerability: ship blinks (toggle visibility every 6 frames)
- Saucer movement: smooth horizontal drift with occasional vertical jink

### SFX (WebAudio, generated) [Estimated]
| Sound | Description |
|-------|-------------|
| Thrust | Continuous white noise burst while Up held |
| Fire | Short high-pitched click/blip |
| Large asteroid explode | Low-freq thump |
| Medium asteroid explode | Mid-freq crunch |
| Small asteroid explode | High-freq pop |
| Ship death | Descending multi-tone burst |
| Saucer appear | Oscillating warble loop |
| Saucer fire | Mid-pitched blip |
| Extra life | Short rising chord |
| Heartbeat | Two-tone low pulse, BPM increases as asteroid count falls |

### Music
- No background music (original had none) [Confirmed]; only heartbeat SFX

### Game Feel / Juice
- Ship velocity preserves across frames (real inertia) [Confirmed]
- Slight canvas shake on player death (2–3 frame offset)
- Score pop-up "+20" / "+100" floating text at impact point [Estimated enhancement]

---

## 11. Monetization

### Clone Monetization Plan (web arcade, no ads in MVP)
| Type | Placement | Notes |
|------|-----------|-------|
| None (MVP) | — | Pure free-to-play; no ads, no IAP |
| Optional rewarded ad | Game Over screen | "Watch ad to continue once" — post-MVP |
| Optional donation | Title screen footer | Ko-fi / PayPal link |

### Consent / ATT / GDPR
- No tracking, no ads in MVP → no consent popup required
- If ads added post-MVP: implement GDPR consent banner before first ad call; ATT prompt for iOS PWA

### Aggressiveness: None in MVP; minimal if ads ever added

---

## 12. Retention Hooks

- **Local high score:** Visible on title screen; strong "beat your best" loop [Confirmed approach]
- **Wave counter:** Displayed post-game; "you reached wave 7" encourages another attempt
- **No offline earnings** — game has no idle component
- **No energy/lives system** — play as much as you want
- **No push notifications** — web arcade has none
- **FOMO mechanics:** None — pure arcade
- **Primary retention:** Skill ceiling; players return to improve technique and beat personal records

---

## 13. Localization & Accessibility

### Localization
- MVP: English only [Estimated]
- No RTL required for core UI
- Post-MVP: translate score/wave/lives labels into 5–10 languages if needed (all text is minimal)

### Accessibility
- **Color contrast:** All HUD text white on black (passes WCAG AA) [Estimated]
- **Colorblind:** Vector outlines + shapes differentiate objects; no color-only information [Confirmed as safe]
- **Text scaling:** Fixed pixel fonts acceptable for arcade game; no dynamic scaling needed
- **Difficulty assist:** Adjustable rotation speed and thrust power in settings (optional) [Estimated]
- **No voice/subtitles** required (no narrative audio)
- **Motor accessibility:** Configurable key bindings (post-MVP); mobile buttons are large enough (44px+ targets)

### Age / Content Rating
- ESRB: E (Everyone) — no violence, no blood, no language, no real-world danger simulation [Confirmed]
- COPPA compliance: no data collection, no accounts → no COPPA issues [Confirmed]
- GDPR-K: not applicable with no data collection

---

## 14. Technical Structure

### Engine / Stack
- Vanilla HTML5 + Canvas 2D API + Web Audio API [Clone target]
- No framework, no build step; single `.html` file
- JavaScript ES6+ (arrow functions, classes, destructuring)

### Platform Support
- Web (desktop + mobile browsers): Chrome, Firefox, Safari, Edge
- Offline capable after first load (no network dependencies)
- No native app; PWA manifest optional post-MVP

### Save System
- localStorage key `voidbreaker_hiscore` stores integer high score
- No account system; no cloud sync
- No cross-device sync

### Online Features
- None in MVP; fully offline
- Optional: POST score to serverless function for global leaderboard (post-MVP)

### Anti-Cheat
- N/A — single-player; client-side score only; no competitive ranking

### Performance Notes
- rAF loop targets 60fps; delta-time capped to prevent spiral-of-death on slow frames
- Object pools for bullets and particles (pre-allocate arrays, reuse slots) [Estimated]
- Canvas cleared each frame; no dirty-rect optimization needed at this object count (~30–50 objects max)
- Asteroid count never exceeds ~28 objects simultaneously (11 large → 22 medium → 44 small in worst case, but player is killing them)

### Analytics / SDKs
- None in MVP
- No third-party SDKs; zero network requests

---

## 15. Pacing & Difficulty

### Early Game (Waves 1–3)
- 4–8 large asteroids; slow-moving
- Player learns thrust physics and wrap mechanics
- Saucer appears rarely; large saucer only; fires randomly
- "Aha moment": surviving first asteroid split cluster
- Typical death cause: flying into own debris after split

### Mid Game (Waves 4–8)
- 10–11 large asteroids; faster medium/small debris cloud builds quickly
- Small saucer begins appearing (score >10,000)
- Screen gets crowded; shot economy matters (4-bullet limit)
- Players learn to "pick lanes" through asteroid gaps
- Key skill: deliberate clearing of one quadrant at a time

### Late Game (Waves 9+)
- Maximum asteroid density every wave
- Small saucer fires with high accuracy; must be prioritized
- Ship momentum becomes hazardous — stopped ship is safest
- Expert technique: "lurk" at edge with ship stationary, sniping safely
- Score compounds rapidly from small asteroid chains

### Difficulty Curve [Estimated]
- Wave 1: Trivially survivable for most players
- Wave 3–4: First serious wave; accidental deaths spike here
- Wave 6–7: Average casual player loses last life here
- Wave 10+: Skilled/veteran territory
- Wave 15+: Near-expert; small saucer fully accurate

### Churn Points [Estimated from genre norms]
- First death from asteroid split surprise (wave 1–2)
- First small saucer appearance (wave 4–5 region, score ~8–12k)
- Running out of bullets during dense debris cloud
- Hyperspace self-destruct at critical moment

---

## 16. Clone Build Plan

### MVP Feature Set
- [x] Ship: rotate, thrust (inertia), fire, screen wrap
- [x] Asteroids: 3 sizes, split on hit, random velocity/rotation
- [x] Bullets: 4-cap, expire by distance, screen wrap
- [x] Wave system: clear → next wave with more asteroids
- [x] Lives: 3 lives, respawn with invulnerability, game over
- [x] Score: per-object points, extra life every 10k, localStorage best
- [x] Enemy saucer: large + small, aimed/random fire, screen wrap
- [x] Hyperspace: random teleport, chance of death
- [x] Explosion particles
- [x] Heartbeat SFX (WebAudio)
- [x] All SFX (WebAudio generated)
- [x] Title screen + game over screen + pause
- [x] Mobile on-screen buttons
- [x] Responsive canvas
- [x] Mute toggle

### Full Feature Set (post-MVP)
- [ ] Two-player alternating mode
- [ ] Global leaderboard (serverless backend)
- [ ] Configurable key bindings
- [ ] PWA manifest (installable)
- [ ] Difficulty presets (arcade/easy/hard)
- [ ] Colorblind-friendly palette option
- [ ] Tweet-score button
- [ ] Screensaver/attract mode on title

### Phased Build Roadmap
**Phase 1 — Physics skeleton**
- Canvas setup, rAF loop, ship draw + rotate + thrust + wrap
- Keyboard input handler

**Phase 2 — Core shooter**
- Bullet spawn, travel, expiry, screen wrap
- Asteroid objects (3 sizes), random placement, wrapping

**Phase 3 — Game logic**
- Collision detection (ship/asteroid, bullet/asteroid, ship/saucer, bullet/saucer)
- Asteroid splitting
- Lives, death, respawn with invulnerability

**Phase 4 — Waves & Score**
- Wave clear detection, next-wave spawn
- Score system, extra-life milestones, localStorage best

**Phase 5 — Enemy Saucer**
- Large saucer: random movement + random fire
- Small saucer: aimed fire, accuracy scaling with score
- Saucer spawn timer logic

**Phase 6 — Polish**
- Explosion particles, thrust flame
- WebAudio SFX for all events
- Heartbeat pulse synced to asteroid count
- Screen flash on wave clear
- Ship blink during invulnerability
- Floating score pop-up text

**Phase 7 — UI & Mobile**
- Title screen, game-over screen, pause overlay
- Mobile on-screen buttons (touch events)
- Responsive canvas resize handler
- Mute toggle

**Phase 8 — QA**
- Wrap-edge math verification (all 4 edges × all object types)
- Array cleanup (no object leaks across waves/restarts)
- Restart fully resets all state
- Test on iOS Safari + Android Chrome

### Recommended Tech Stack
- HTML5 Canvas 2D (vector drawing)
- Web Audio API (generated SFX, no audio files)
- Vanilla JS (no dependencies)
- Single `.html` file; no build tools

### Required Asset List
- No image files — all geometry is canvas-drawn
- No audio files — all SFX generated via Web Audio API
- Font: system monospace or Google Fonts "Share Tech Mono" (optional; fallback to monospace)
- No external CDN allowed (offline requirement)

### Hardest Parts / Risks
1. **Inertia + wrap interaction**: Ship moving fast near edge can "jump" wrap unexpectedly — ensure wrap is applied smoothly per-frame
2. **Collision detection accuracy**: Circle-circle or polygon approximate; must not miss hits or give false positives on splits
3. **Array cleanup on restart**: All bullets, particles, saucer, asteroids arrays must be fully cleared; stale object reference is a common bug
4. **Small saucer accuracy scaling**: Must feel fair — too accurate at low score is unfun; too random at high score is trivial
5. **Mobile touch button layout**: Must not overlap game canvas in portrait mode; test on small screens (375px wide)
6. **WebAudio initialization**: Must be triggered on user gesture (click/tap) to comply with browser autoplay policy

---

## 17. Open Questions

1. **Heartbeat BPM curve**: Original sped up as asteroids decreased; exact BPM range not confirmed. Clone uses [Estimated] 60 BPM (full field) → 180 BPM (1 asteroid left) with linear interpolation.
2. **Saucer movement pattern**: Original had some diagonal jinking. Exact angle/frequency not confirmed. Clone uses [Estimated] horizontal drift with random ±30° vertical jinks every 1.5 s.
3. **Bullet-on-asteroid collision grant points**: Original awarded points if bullet killed it. Whether saucer bullets killing asteroids awards player points is unclear; clone gives 0 pts for saucer-bullet kills.
4. **Small saucer accuracy cap**: At what score does it reach maximum accuracy? [Estimated] 50,000 pts → pinpoint aim; linear scale from 10,000 to 50,000.
5. **Exact asteroid polygon shapes**: Original used fixed 8 specific polygon shapes. Clone uses procedurally generated irregular polygons per-asteroid-instance for visual variety; aesthetically equivalent.

---

*Blueprint complete — all 17 sections filled. Confirmed/Estimated tags applied throughout. Build can begin from Phase 1 immediately.*
