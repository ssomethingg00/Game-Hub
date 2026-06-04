# SwiftStride Endless Runner — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

SwiftStride is a side-scrolling endless runner where an auto-running character must survive as long as possible by jumping over ground obstacles (cacti clusters) and ducking under flying obstacles (birds). Speed increases over distance, making the game progressively harder until failure is inevitable. Score equals distance traveled. The appeal is pure reflex testing: simple controls, immediate replayability, and the compulsive drive to beat a personal best. [Confirmed — based on Chrome T-Rex Runner design, original genre archetype]

**Quick facts:**
- Genre: Endless Runner / Arcade Reflex
- Platforms: Web (HTML5 Canvas), Mobile (touch), Desktop (keyboard)
- Comparable titles: Chrome T-Rex Runner, Temple Run, Subway Surfers (simplified), Canabalt
- Session length: 15 seconds – 5 minutes
- Play style: Active, single-player, reflex-based
- Age/content rating: IARC Everyone (E) — no violence, no data collection [Estimated]
- Monetization model: None (score-only web game) [Estimated for this clone]

---

## 2. Core Loops

- **30-second loop:** Character runs automatically. Player taps/presses Space to jump over ground cacti clusters; presses Down/swipes down to duck under flying birds. Each near-miss builds tension. Speed increases every few seconds. One collision = game over screen. Tap to restart.
- **Session loop:** Start → survive as long as possible → die → see score vs. best score → instant restart. Typical session is 1–5 plays, 1–10 minutes total. Trying to beat personal best is the core driver.
- **Meta loop:** No long-term meta (pure arcade). Persistence is limited to localStorage high score. The meta hook is the personal high score wall — players return to beat it. [Estimated]

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
1. **Auto-run:** Character moves rightward at constant velocity relative to the world; the ground and background scroll left past the camera.
2. **Jump:** Character launches upward with initial vertical velocity; gravity pulls back down. Holding jump key does NOT extend jump (fixed arc). Single jump only (no double jump). [Confirmed — Chrome Dino behavior]
3. **Duck:** While on the ground (or in air for a fast drop), character shrinks hitbox to half height, slides under flying obstacles. Duration = held duration.
4. **Obstacle collision (AABB):** If character bounding box intersects any obstacle bounding box → game over.
5. **Speed ramp:** Game speed starts at ~6 px/frame (at 60 FPS) and increases by a small increment per 100 points, capping near ~13–14 px/frame at ~900 points. [Confirmed — Chrome Dino source data]
6. **Day/night palette shift:** At milestone scores (e.g., every 700 points), background and foreground colors invert between a light-day and dark-night palette. [Confirmed]
7. **Parallax scrolling:** Background layer (distant hills/clouds) scrolls slower than ground to create depth illusion. [Estimated]
8. **Score:** Distance in pixels divided by a scale factor, displayed as integer. Increments faster as speed increases.

### Game Modes
- **Endless Mode (only mode):** Single endless survival run. No levels, no checkpoints.

### Controls

| Action | Keyboard | Mobile Touch |
|--------|----------|-------------|
| Jump | Space / ArrowUp / W | Tap anywhere (swipe up) |
| Duck | ArrowDown / S | Swipe down / hold lower area |
| Restart (game over) | Space / Enter | Tap anywhere |
| Start game | Space / Enter | Tap anywhere |
| Mute toggle | M | Mute button UI |

- **Orientation:** Landscape strongly preferred; portrait supported with scaled canvas.
- **Input note:** Jump is single-press triggered (not hold to float). Duck is hold-triggered.

### Game States
1. **START_SCREEN** — Title, controls hint, "tap/press to begin"
2. **PLAYING** — Active gameplay, HUD visible, scrolling active
3. **GAME_OVER** — Character collision animation, score/best display, restart prompt
4. (No pause state in base design) [Estimated]

### Win / Lose / Fail Conditions
- **Win:** None — endless game, no win state.
- **Fail:** AABB collision between character and any obstacle → GAME_OVER state.
- **Failure handling:** Immediate stop of scrolling, "Game Over" overlay with current score and high score. No lives, no continues. Instant restart on input.

### AI / Opponent
- No AI opponents. Obstacles are procedurally spawned with randomized gap timing and obstacle type selection.

### Difficulty Modes
- Single difficulty, continuously increasing. No manual difficulty selection. [Confirmed]

### Feedback Systems
- **Visual:** Character brief flash/shake on game over; score pulses on milestone (every 100 pts); day/night palette flip at ~700-pt intervals.
- **Audio:** Jump SFX (blip), duck SFX (whoosh), game-over SFX (low thud), milestone ding, background ambient (optional loop).
- **Haptic:** Not implemented in web version.

---

## 4. Progression

- **No XP, levels, or unlock trees.** Pure arcade — every run is fresh with no persistent upgrades.
- **Implicit difficulty progression:** Speed increases linearly with distance until a hard cap. [Confirmed]
- **Milestones:** Visual/audio cue every 100 points. Day/night flip every ~700 points. [Confirmed milestone cues; frequency Estimated]
- **High score persistence:** Best score stored in localStorage — only persistent progression element.
- **Gating:** None. Character, obstacles, and mechanics are all available from frame 1.

---

## 5. Economy & RNG *(tables)*

### Currencies

| Currency | Type | Earned From | Spent On |
|----------|------|-------------|---------|
| Score (distance) | Display-only | Auto-increments during run | Nothing — display/personal best only |

No in-game economy. No currencies to spend. No IAP. [Confirmed — pure arcade score game]

### Speed Scaling Formula

```
speed(score) = MIN(MAX_SPEED, BASE_SPEED + ACCEL_RATE × (score / 100))

BASE_SPEED = 6.0 px/frame
ACCEL_RATE = 0.5 px/frame per 100 score
MAX_SPEED  = 13.0 px/frame (reached ~≥900 score)
```
[Confirmed values from Chrome Dino reverse engineering; Estimated accel_rate tuning]

### Obstacle Spawn Formula

```
min_gap_px = MAX(MIN_GAP, BASE_GAP - (speed × GAP_REDUCTION))

BASE_GAP      = 600px (screen-relative)
MIN_GAP       = 250px
GAP_REDUCTION = 8
```
Spawn type is random: ~65% ground obstacle, ~35% flying obstacle (flying introduced after score ≥ 300). [Estimated distribution; Confirmed flying-enemy threshold around 700 in Chrome Dino, reduced here for pacing]

### RNG / Drop Rates

| Element | Randomization |
|---------|---------------|
| Obstacle gap size | Uniform random in [MIN_GAP, BASE_GAP] |
| Ground obstacle variant | Random choice: 1 cactus, 2 cacti, 3 cacti, wide cactus |
| Flying obstacle height | Random: high (needs jump to clear safely) or low (needs duck) |
| No loot, gacha, or drop rates | N/A |

---

## 6. Content Inventory *(counts + lists)*

### Obstacles
- **Ground obstacles (4 variants):** Single cactus (narrow), double cactus, triple cactus cluster, wide flat cactus [Estimated variants]
- **Flying obstacles (2 heights):** Bird at high altitude (jump clearable / duck not needed), bird at low altitude (must duck) [Confirmed — height forcing decision]

### Characters
- 1 player character: "Swift" — stylized running figure (stick/pixel/vector sprite)
  - Animation states: Run (2-frame cycle), Jump, Duck, Die
- No unlockable characters in MVP [Estimated]

### Environments
- 1 endless ground track (procedural)
- 2 palette themes: Day (light bg, dark elements) / Night (dark bg, light elements)
- Background decoration: distant rolling hills or cloud shapes (parallax layer, 1–2 variants)

### Levels / Stages
- 1 endless stage, no discrete levels

### Collections / Cosmetics
- None in base design [Estimated]

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract minimalist landscape — flat desert-road aesthetic (inspired by Chrome Dino but re-skinned; no dinosaurs or Google branding). Original character is a running humanoid figure, "Swift."
- **Premise:** No story. Pure reflex arcade — Swift runs forever; player keeps them alive.
- **Story delivery:** None.
- **Character personality:** Neutral, athletic — expressed through animation (energetic run cycle, dramatic stumble on death).
- **Dialogue / writing style:** Minimal UI text only. Score labels, "GAME OVER", "BEST", "TAP TO START". No dialogue.
- **Tone:** Clean, minimalist, slightly retro. Calm during run; tense as speed increases. No violence — character simply stumbles and stops.
- **IP:** Wholly original. No licensed assets. [Estimated]
- **Color palette (day):** Off-white background (#F5F0E8), charcoal elements (#2C2C2C), accent coral (#E85D3B)
- **Color palette (night):** Dark slate (#1A1A2E), light cream elements (#E8E0D0), accent blue (#4CC9F0)

---

## 8. Meta & Social Systems

- **Daily rewards:** None [Confirmed — no meta progression]
- **Missions / quests:** None
- **Achievements:** None in base design (can add: "First 100", "Night Shift", etc.) [Estimated]
- **Limited-time events:** None
- **Battle pass / seasons:** None
- **Live-ops cadence:** N/A — static web game
- **Leaderboards:** None in base MVP (could add localStorage-only top-5) [Estimated]
- **Multiplayer / PvP / co-op:** None
- **Sharing / referral:** None
- **Social features:** None
- **Decoration / base building:** None

---

## 9. UI / UX & Screen Map

### Screen List

| Screen | Purpose |
|--------|---------|
| Start Screen | Title, control instructions, "Tap or press Space to begin" |
| Gameplay Screen | Active run — HUD + game canvas |
| Game Over Overlay | Score, best score, restart prompt (overlays gameplay screen) |
| (No settings screen in MVP) | Mute toggle in HUD only |

### Settings / Options
- Mute toggle (M key or button): toggles WebAudio SFX/music on/off
- No language selector, no account, no restore purchases (no monetization)

### Gameplay HUD Elements
- **Top-left:** Current score (distance integer)
- **Top-right:** Best score (HI: XXXXX)
- **Top-right corner:** Mute/unmute button (speaker icon)
- **Center (brief flash):** Milestone score popups ("+100!", "+500!", etc.) [Estimated]
- **No lives indicator** (single life only)

### Navigation Flow
```
[Start Screen]
      |
   (input)
      ↓
[Gameplay Screen] ──(collision)──→ [Game Over Overlay]
                                          |
                                       (input)
                                          ↓
                                  [Gameplay Screen] (new run)
```

### Onboarding / Tutorial
1. First run: Start screen shows "SPACE / TAP = Jump  |  DOWN / Swipe = Duck"
2. First obstacle spawned slightly later than usual (grace distance ~300px) [Estimated]
3. No forced tutorial — player learns by dying
4. No FTUE modal, no hand-holding — immediate play after input

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D side-scroller
- **Camera / perspective:** Fixed side-on (character stays at left 20% of screen horizontally, world scrolls left)
- **Orientation:** Landscape preferred (16:9); portrait-safe with viewport scaling
- **Art style:** Minimalist vector / geometric. Flat shapes, no gradients, no textures. [Estimated]
- **Color palette:** As defined in Section 7. Day/night flip at milestones.
- **Character animation:**
  - Run: 2-frame leg cycle (simplified stride)
  - Jump: single upward-arc sprite
  - Duck: crouched sprite (half height)
  - Die: stumble/fall sprite + brief screen flash
- **Obstacles:** Simple geometric shapes — cacti as stacked rectangles, birds as V-shape or simple winged silhouette
- **Background:** Flat color sky, thin horizon line, slow-parallax distant shapes (hills/clouds)
- **Ground:** Single horizontal line + thin strip fill, scrolling at game speed
- **VFX:**
  - Small dust puff on landing
  - Red flash overlay on collision (screen)
  - Score milestone text pop (scale + fade)
  - Day/night transition: smooth 0.5s palette lerp [Estimated]
- **SFX (WebAudio generated — no files):**
  - Jump: short ascending sine blip (~80ms)
  - Duck: soft whoosh (filtered noise ~100ms)
  - Land: soft thump (low frequency ~60ms)
  - Hit/death: descending buzz ~200ms
  - Milestone ding: short bell tone
  - Background: optional subtle drone (toggleable)
- **Music:** Optional simple procedural ambient loop (WebAudio oscillators) or silence [Estimated]
- **Juice:** Screen micro-shake on death, score counter animation, speed-up visual cue (slight motion blur or increased parallax rate)

---

## 11. Monetization

- **No monetization in this web arcade clone.** [Confirmed — by design brief]
- No ads (no banner, interstitial, rewarded)
- No IAP
- No loot boxes / gacha
- No subscriptions / battle pass
- No ATT prompt, no GDPR/CMP consent popup required (no tracking, no ads)
- No age gate required
- **Revenue model:** N/A — free to play, no transactions

---

## 12. Retention Hooks

- **Primary hook:** Personal high score in localStorage — visible on start screen and game-over screen. Drives "one more run" motivation.
- **Daily rewards:** None [Confirmed]
- **Idle / offline earnings:** None — active play only [Confirmed]
- **Push notifications:** None (web game) [Confirmed]
- **Energy / lives system:** None — unlimited instant restarts [Confirmed]
- **FOMO mechanics:** None
- **Secondary hook:** Speed escalation creates tension and urgency naturally; each run feels faster and harder.
- **Tertiary hook:** Day/night palette shifts create a sense of journey within a single run.

---

## 13. Localization & Accessibility

- **Languages:** English only in MVP (minimal text — easily expandable) [Estimated]
- **RTL support:** N/A (no text-heavy UI)
- **Text scaling:** Canvas-rendered text scales with canvas — no system font scaling issues
- **Colorblind modes:** Not implemented in MVP; day palette has sufficient contrast; consider adding high-contrast toggle [Estimated]
- **Difficulty / assist options:** None in MVP; could add "slow mode" toggle [Estimated]
- **Subtitle / audio options:** Mute toggle only
- **Age/content rating:** IARC Everyone — suitable all ages, no mature content
- **COPPA / GDPR-K compliance:** No data collected (localStorage only, no PII, no analytics, no ads) — COPPA-safe by design [Confirmed]
- **Regional differences:** None — web game, no store, no regional pricing

---

## 14. Technical Structure

- **Engine / framework:** Vanilla JavaScript, HTML5 Canvas 2D API — no framework, no build step [Confirmed — per design brief]
- **Language:** JavaScript (ES6+)
- **Platforms:** Any modern browser (Chrome, Firefox, Safari, Edge); desktop + mobile
- **Online / offline:** Fully offline — no network requests, no CDN dependencies [Confirmed]
- **Save system:** localStorage only — stores single high score integer. Key: `swiftstride_best` [Estimated]
- **Accounts / auth:** None
- **Cross-device sync:** None (localStorage is device-local)
- **Multiplayer / netcode:** None — single-player only
- **Anti-cheat:** N/A — no competitive online component
- **Backend services:** None
- **Analytics:** None (no tracking)
- **Remote config / A/B testing:** None
- **Third-party SDKs:** None
- **Performance:**
  - requestAnimationFrame loop (not setInterval)
  - Canvas cleared and redrawn each frame
  - Object pooling for obstacles (recycle off-screen objects) [Estimated]
  - Target: stable 60 FPS on mid-range mobile
- **App size:** Single .html file ~50–80KB [Estimated]
- **Canvas scaling:** CSS `width:100%; height:100%` + logical 800×300 coordinate space, scaled to viewport with `canvas.width/height` and `devicePixelRatio` for crispness [Estimated]

---

## 15. Pacing & Difficulty

### Early Game (score 0–300)
- Slow speed (~6 px/frame); only ground obstacles spawn
- Gap spacing generous (500–600px); player has ample reaction time
- Learning phase: player discovers jump timing, get used to scroll speed
- "Aha" moment: first successful jump over a tall cactus cluster

### Mid Game (score 300–700)
- Speed climbing (~8–10 px/frame); flying obstacles introduced at ~300 score
- Gap spacing tightening; player must read obstacle type fast (jump vs duck decision)
- "Aha" moment: first successful duck under a low bird
- Day/night flip around 700 adds visual novelty, slight disorientation

### Late Game (score 700+)
- Speed near cap (~11–13 px/frame); reaction windows very short
- Mixed ground + flying obstacles; occasional close sequences requiring jump-then-immediately-duck
- Night palette sustained longer; further flips every 700 pts
- Churn point: most casual players fail before 500; experienced players fail ~1000–2000 [Estimated]
- Human reaction-limit wall: at max speed (~13 px/frame @ 60 FPS = 780 px/sec) response window for a 60px obstacle is ~77ms — near human reflex limit [Confirmed physics; Estimated numbers]

### Difficulty Curve
```
Score:    0      100    300    500    700    900+
Speed:    6.0    6.5    7.5    8.5    10.0   13.0  (px/frame)
Gap min:  600    560    500    420    350    250   (px)
Flying:   No     No     Yes    Yes    Yes    Yes
```
[Estimated curve, tuned for fun difficulty arc]

---

## 16. Clone Build Plan

### MVP Feature Checklist

**MVP (playable core):**
- [x] HTML5 Canvas game loop (rAF)
- [x] Auto-scrolling ground + parallax background
- [x] Player character: run/jump/duck/die states
- [x] Gravity-based jump arc
- [x] Ground obstacles (cactus, 3 variants)
- [x] Flying obstacles (bird, 2 heights)
- [x] AABB collision detection → game over
- [x] Speed ramp with distance
- [x] Score counter (distance)
- [x] localStorage high score persistence
- [x] Start screen + game over screen
- [x] Space/Up to jump, Down to duck (keyboard)
- [x] Tap to jump, swipe-down to duck (touch)
- [x] WebAudio SFX (jump, duck, die, milestone)
- [x] Mute toggle
- [x] Day/night palette shift
- [x] Responsive canvas scaling

**Full Feature Set (post-MVP):**
- [ ] Multiple characters (unlockable via score milestones)
- [ ] Power-ups (brief invincibility, score multiplier)
- [ ] Achievement system
- [ ] Local top-5 leaderboard
- [ ] Animated sprite sheets (vs. procedural geometry)
- [ ] Particle system (dust, feathers on bird hit)
- [ ] Idle background music (procedural)
- [ ] Colorblind / high-contrast mode
- [ ] Share score button (Web Share API)
- [ ] PWA manifest (installable web app)

### Phased Build Roadmap

**Phase 1 — Core Loop (Day 1–2)**
- Canvas setup, rAF loop, ground scroll
- Player geometry + gravity physics (jump arc)
- Static obstacle render + AABB collision
- Basic game states (start, play, gameover)
- Keyboard controls

**Phase 2 — Content & Difficulty (Day 2–3)**
- Obstacle spawner with gap/type randomization
- Speed ramp formula
- Flying obstacle + duck mechanic
- Score counter + localStorage best
- Touch/swipe controls

**Phase 3 — Polish & Feel (Day 3–4)**
- Day/night palette system + transition
- Parallax background layer
- WebAudio SFX generation
- Screen shake on death, milestone flash
- Mute toggle, HUD refinement

**Phase 4 — QA & Optimization (Day 4)**
- Hitbox fairness pass (shrink character hitbox ~20% for feel)
- Obstacle pool cleanup (remove off-screen objects)
- Mobile viewport scaling / devicePixelRatio
- Console error sweep, cross-browser test
- Final file size check

### Recommended Tech Stack
- **Language:** Vanilla JS (ES6+) — no framework needed
- **Rendering:** HTML5 Canvas 2D
- **Physics:** Manual gravity (vy += gravity each frame)
- **Audio:** Web Audio API (oscillators + filters — zero external files)
- **Storage:** localStorage
- **Build:** None — single .html file, zero dependencies

### Required Asset List
All assets generated procedurally in Canvas (no external files):
- Player geometry (rectangles + arcs for head)
- Cactus geometry (stacked rectangles)
- Bird geometry (V-shape wings + body rect)
- Ground line
- Background hill shapes
- Color palettes (day/night, 6 colors each)
- Font: system sans-serif (no external font)
- Audio: WebAudio oscillator synthesis

### Hardest Parts / Risks
1. **Hitbox fairness** — AABB on full sprite feels unfair; must shrink hitboxes ~15–25% inward so near-misses feel satisfying, not cheap deaths. [Risk: HIGH]
2. **Touch duck detection** — distinguishing tap (jump) from swipe-down (duck) requires gesture threshold; too sensitive = false ducks, too insensitive = missed ducks. [Risk: MEDIUM]
3. **Speed ramp feel** — linear ramp can feel abrupt; needs playtesting to ensure it's challenging but fair through the 300–700 score range. [Risk: MEDIUM]
4. **Canvas scaling on mobile** — must handle devicePixelRatio correctly for sharp rendering on retina displays without blowing up performance. [Risk: LOW-MEDIUM]
5. **Obstacle solvability guarantee** — spawn logic must guarantee every obstacle sequence is physically beatable (no impossible gap-to-gap or fly+ground same-x combos). [Risk: MEDIUM]

---

## 17. Open Questions

1. **Optimal hitbox shrink factor** — ~20% reduction is estimated based on genre norms; exact value needs playtesting to balance fairness vs challenge. [Needs: playtesting]
2. **Flying obstacle introduction score** — set at 300 in this blueprint; if early game feels too hard, push to 400. [Needs: playtesting]
3. **Background music preference** — blueprint includes optional procedural ambient drone; whether this enhances or distracts depends on target audience. [Needs: user feedback]
4. **Mobile portrait layout** — landscape is primary; portrait will letterbox. Worth building a rotation prompt for portrait phones. [Needs: UX decision]

---

*Blueprint version 1.0 — SwiftStride Endless Runner — June 2026*
*Mechanics and systems cloned from the endless runner genre (public domain design patterns). All assets, branding, and audio are original. No copyrighted materials reproduced.*
