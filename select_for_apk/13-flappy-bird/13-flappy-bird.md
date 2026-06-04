# Sky Drifter (Flappy Bird Style) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

**Sky Drifter** is a single-button infinite-runner arcade game in which the player guides a small bird-like character through an endless series of pipe-gap obstacles by tapping/clicking to flap upward against constant gravity. The appeal is pure simplicity: one input, brutal difficulty, addictive score chasing. The original that inspired this genre was *Flappy Bird* (Dong Nguyen / .GEARS, 2013), a hyper-casual mobile phenomenon that spawned hundreds of clones. Sky Drifter is an original-branded re-skin with its own visual identity (a glowing celestial "Star Sparrow" theme).

| Fact | Value |
|---|---|
| Original inspiration | Flappy Bird (Dong Nguyen, .GEARS, 2013) [Confirmed] |
| Clone title | Sky Drifter |
| Genre | Hyper-casual, endless arcade, single-button platformer |
| Platforms | Web (HTML5 Canvas), easily portable to iOS/Android WebView |
| Session length | 10–60 seconds per run [Estimated] |
| Play style | Active, single-player, score-attack |
| Content rating | IARC Everyone (no violence, no mature content) [Estimated] |
| Monetization model | Optional (ad banner + rewarded ad for continue; no IAP required) |

---

## 2. Core Loops

- **30-second loop:** Tap/click/Space repeatedly to flap the bird upward; gravity pulls it down continuously; scroll left through procedurally-placed pipe pairs; each pair safely passed = +1 score; touch anything (pipe edge, ground, ceiling) = instant game over screen; tap to restart immediately.
- **Session loop:** Player attempts run → dies → sees score vs best → taps to retry → marginal improvement motivates one-more-try → session ends when player puts down device or beats personal best.
- **Meta loop:** Personal best score stored in localStorage; beating it provides the primary extrinsic reward. Optional daily challenge score variant (same seed each day) encourages return. No account required; friction-free restart keeps daily active sessions high.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
- **Gravity:** Constant downward acceleration applied every frame to the bird's Y velocity. [Confirmed genre norm]
- **Flap impulse:** Single tap/click/key applies an instant upward velocity delta (negative Y), counteracting gravity for ~0.4–0.6 seconds before gravity dominates again. [Estimated]
- **Pipe scrolling:** Pipe pairs spawn off-screen right, scroll left at a fixed speed, despawn off-screen left. Gap position is randomized within safe vertical bounds each spawn. [Confirmed genre norm]
- **Gap scoring:** When a pipe pair's right edge crosses the bird's X position, score increments by 1 and a sound/visual cue fires. [Confirmed genre norm]
- **Collision detection:** AABB (axis-aligned bounding box) with a slight inset margin (forgiveness ~4 px) on the bird hitbox. Collision with pipe body, top edge, ground, or ceiling = death. [Estimated]
- **Difficulty ramp:** Pipe scroll speed increases subtly every 5 pipes passed; gap height decreases subtly every 10 pipes. Both capped at max values so the game remains physically possible. [Estimated]

### Core Verbs
- Flap (tap / click / Space / Up arrow)
- Watch / react (pattern recognition, timing)

### Game Modes
| Mode | Description |
|---|---|
| Classic Endless | Standard infinite run; score = pipes passed; ends on any collision |
| (Optional) Daily Challenge | Same random seed per calendar day; leaderboard score for that day |

### Input Scheme
| Action | Input |
|---|---|
| Flap | Touch anywhere on screen (mobile) |
| Flap | Left mouse click anywhere |
| Flap | Spacebar |
| Flap | Up arrow key |
| Restart | Same as flap (any of the above, on game-over screen) |

**Orientation:** Portrait (mobile-first); landscape also works but portrait is canonical.

### Win / Lose / Fail States
- **Win:** No win state — game is infinite by design. Personal best is the win condition.
- **Lose / Fail:** Any collision with pipe, ground, or ceiling. Bird drops to ground with brief death animation (rotation/flash). Game-over screen appears immediately.
- **Failure handling:** Game-over screen shows current score + all-time best. Tap/click/Space restarts immediately. No lives, no energy, no continue (or optional rewarded-ad continue for +3 seconds of invincibility). [Estimated]

### AI / Opponent Behavior
None — purely environmental obstacles. Pipes are procedurally generated with deterministic-within-session RNG.

### Difficulty Modes
Single difficulty (no settings menu option); implicit ramp via speed/gap changes described above.

### Feedback Systems
- **Visual:** Bird rotation follows velocity vector (nose up on flap, nose down in freefall). Flash white on death. Score pop (+1 number floats up briefly). Screen shake on death. Particle burst on death.
- **Audio (WebAudio, generated):** Soft "whoosh" on flap; cheerful "ping" on score; thud/crunch on death; background ambient loop (optional/mutable).
- **Haptic:** Vibration on death on mobile (navigator.vibrate if available).

---

## 4. Progression

### Score-Based Milestones [Estimated]
| Score | Event |
|---|---|
| 1 | First pipe — tutorial complete |
| 5 | Speed increase tick 1 |
| 10 | Gap height decrease tick 1; speed tick 2 |
| 20 | Speed tick 3; gap tick 2 |
| 40 | Maximum difficulty reached (pipe speed cap + min gap) |

### Unlock / Upgrade Trees
None in MVP. Optional: cosmetic bird skins unlocked at score milestones (5, 10, 25, 50) stored in localStorage.

### Prestige / Rebirth
None.

### Gating
No hard gates. Soft gate: gap shrinkage around score 20 creates a natural difficulty wall where most casual players churn.

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Score | In-run only | Each pipe passed | Nothing (display only) |
| Best Score | Persistent (localStorage) | Beating previous best | Bragging rights / skin unlocks (optional) |

No soft/hard currency. No IAP economy in MVP. [Confirmed genre norm for HTML5 web clones]

### Cost Scaling
No upgrades to purchase. N/A.

### RNG / Drop Rates
| Variable | RNG Type | Range | Notes |
|---|---|---|---|
| Gap vertical center | Uniform random | 30%–70% of canvas height | Avoids extremes so gap is always reachable [Estimated] |
| Gap height | Fixed per tier; decreases at milestones | 160px → 120px → 100px | Minimum 100px to keep game fair [Estimated] |
| Pipe spawn interval | Fixed (timer-based) | ~1.6s between pairs | Consistent rhythm; no RNG in timing [Estimated] |

No gacha, no loot boxes, no drop rates beyond pipe gap position.

---

## 6. Content Inventory *(counts + lists)*

### Levels / Stages
- 1 infinite procedural level (no distinct stages)
- Background: gradient sky (dawn → dusk color shift every 30 pipes) [Estimated]

### Characters
- **Star Sparrow** — the player bird. Glowing, round, celestial theme with small wings.
- No NPCs, no enemies (pipes are environmental obstacles).

### Obstacles
- **Pipe pairs:** Single obstacle type; upper pipe + lower pipe with random-height gap. Green metal aesthetic in original; Sky Drifter uses glowing purple crystal pillars.

### Items / Collectibles
- None in MVP.
- Optional: golden feathers as collectibles for bonus score (+5) [Estimated future feature]

### Collections / Cosmetics (Optional)
| Skin | Unlock Condition |
|---|---|
| Star Sparrow (default) | Always available |
| Ember Phoenix | Score 10+ once |
| Frost Owl | Score 25+ once |
| Thunder Hawk | Score 50+ once |

---

## 7. Theme, Narrative & Tone

**Setting:** A fantastical sky realm — clouds, glowing crystals, aurora-lit background. No ground lore; purely aesthetic.

**Premise:** No story. The experience IS the game — a tiny bird endlessly drifting through crystalline obstacles in a magical sky. The charm is in the contrast: adorable protagonist vs punishing physics.

**Character Personalities:** The bird is expressive — happy animation on flap, scared on near-miss (future feature), sad/dead animation on collision. No dialogue.

**Writing Style:** UI text is punchy and encouraging ("TAP TO FLY", "NICE!", "SO CLOSE", "BEST!"). Short, caps, exclamatory. No narrative text.

**Tone:** Cute + frustrating (intentionally). Cheerful palette undercuts the brutal difficulty. Relaxing ambient audio contrasts with sudden death. The "just one more try" compulsion loop is the entire design intent.

**IP:** 100% original — no licensed characters, no trademarks from original Flappy Bird.

---

## 8. Meta & Social Systems

### Daily Missions / Achievements (Optional)
| Achievement | Condition |
|---|---|
| First Flight | Pass 1 pipe |
| High Flyer | Score 10 |
| Cloud Surfer | Score 25 |
| Legend | Score 50 |

Stored in localStorage; shown on game-over screen as badge unlocks.

### Events
None in MVP (static, serverless HTML5 game). Optional: hardcoded "daily seed" using date for daily challenge mode.

### Live-Ops Cadence
Minimal — this is a self-contained HTML5 file. No server required. No ongoing content burden. Clone operator can update the HTML file ad-hoc.

### Leaderboards / Multiplayer
None in MVP (no server). Optional: integrate a free leaderboard API (e.g., lootlocker.io) as Phase 2 feature.

### Social Sharing
Optional: on game-over, "Share Score" button opens a pre-filled tweet: "I just scored [X] in Sky Drifter! Can you beat it? [URL]" — uses window.open to Twitter intent URL.

---

## 9. UI / UX & Screen Map

### Screens
| Screen | Purpose |
|---|---|
| Start Screen | Title, best score display, "TAP TO FLY" prompt, mute button, optional skin selector |
| Gameplay HUD | Live score (top-center), mute button (top-right) |
| Game Over Screen | "GAME OVER" title, current score, best score, "tap to restart" prompt, achievement unlocks |
| (Optional) Settings Modal | Mute toggle, controls reminder, credits/attribution |

### HUD Elements During Gameplay
- Score (large, centered top) — current pipe-pass count
- Mute icon (top-right corner)
- No health bar, no currency display

### Navigation Flow
```
Start Screen → [tap/click/space] → Gameplay → [collision] → Game Over → [tap/click/space] → Gameplay
                                                                         ↑ loops indefinitely
```

### Onboarding / Tutorial (First Run)
1. Start screen appears with animated bird bouncing in place.
2. "TAP TO FLY" pulsing text + pointer-finger icon (mobile) / spacebar icon (desktop).
3. First pipe pair spawns slightly delayed (extra 1 second) on very first run to give player time to learn flap feel. [Estimated]
4. No further tutorial — game is self-explanatory.

### Popups / Modals
- Achievement unlock toast (bottom of screen, 2 seconds, first time only).
- New best score flash ("NEW BEST!" in gold) on game-over screen when beaten.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D side-scrolling**
- Camera is fixed; world scrolls left past a stationary bird X position (~25% from left edge)
- Portrait orientation primary; landscape supported

### Art Style
- **Flat + slightly rounded cartoon** — bold outlines, vibrant colors, no realistic texturing
- Canvas drawn entirely with JavaScript (no external image files)
- Shapes: circles/ellipses for bird body, rectangles + rounded caps for pipes, gradient fills for sky

### Color Palette [Estimated]
| Element | Color |
|---|---|
| Sky (top) | #1a0533 (deep purple) |
| Sky (bottom) | #ff6b35 (sunset orange) |
| Bird body | #FFD700 (gold) with white glow |
| Bird eye | #fff / #222 |
| Pipes | #7c3aed (violet) with #a78bfa highlight |
| Ground | #2d1b69 (dark purple-brown) |
| Score text | #ffffff with drop shadow |
| Pipes top cap | #5b21b6 |

### Animation Set
| Animation | Description |
|---|---|
| Flap | Wing angle changes; slight upward bob |
| Freefall | Gradual nose-down rotation (max ~70°) |
| Death | Flash white → red tint, drop to ground |
| Idle (start screen) | Gentle hover oscillation |
| Pipe pass | Score number floats up from bird position |

### VFX & Juice
- Screen shake (2–3 frames, ~8px offset) on death
- Particle burst (10–15 feather/star particles) on death
- Score pop: "+1" floating text fades out over 0.5s
- Slight motion blur via canvas globalAlpha trail effect on bird [Estimated]

### Audio (WebAudio API, procedurally generated — no files)
| Sound | Type | Description |
|---|---|---|
| Flap | Tone burst | Short 220Hz sine, 80ms, fast decay |
| Score | Tone | 880Hz sine, 120ms, cheerful ping |
| Death | Noise burst | White noise filtered low, 300ms |
| Ambient | Tone loop | Soft pad chord, 4s loop, very low volume |

**Mute toggle** saves preference to localStorage.

---

## 11. Monetization

### MVP (HTML5 Web, No Ads)
No monetization required for the standalone HTML file. The game runs fully offline/locally.

### Optional Ad Integration (Phase 2)
| Ad Type | Placement | Frequency |
|---|---|---|
| Interstitial | Game-over screen, every 3rd death [Estimated] | Max 1 per 2 minutes |
| Rewarded Video | "Watch ad to continue" button on game-over | Player-triggered only |
| Banner | Bottom of game-over screen (not during gameplay) | Constant on game-over |

### IAP
None. No premium currency, no remove-ads IAP in MVP.

### Consent / ATT / CMP
If ads are added: implement GDPR consent banner (EU users) before first ad request. No ATT prompt needed for web (only native iOS apps). No data collected in MVP (localStorage is device-local). [Estimated]

### Aggressiveness
Minimal in MVP (no ads). If ads added: rewarded ad is always opt-in, interstitial capped to avoid user hostility.

---

## 12. Retention Hooks

### Daily Rewards
None in MVP. Optional: "Daily Challenge" mode (same seed per day) encourages daily return.

### Offline / Idle Earnings
None — this is an active arcade game with no idle mechanic.

### Push Notifications
None (web game, no service worker in MVP). Optional Phase 2: Web Push API "Come beat your best!" notification.

### FOMO / Urgency
- Personal best score displayed prominently on game-over creates inherent FOMO/competitive drive.
- "NEW BEST!" flash when record is beaten reinforces dopamine loop.
- Achievement system (score milestones) provides short-term goals.

### Energy / Lives
None — infinite retries, no wait timers. Deliberate design choice to maximize "one more try" sessions.

---

## 13. Localization & Accessibility

### Languages
- MVP: English only (minimal text — UI labels only)
- Easy to localize: fewer than 20 strings total

### RTL Support
Not required (minimal text, no paragraphs).

### Text Scaling
Canvas-rendered text scales with canvas size (responsive). No system font dependency.

### Colorblind Modes
MVP: No explicit colorblind mode. Palette chosen with contrast (white score on dark sky). Optional: high-contrast toggle.

### Difficulty / Assist Options
None in MVP. Optional: "Easy Mode" toggle that increases gap size and reduces speed cap.

### Age / Content Rating
- IARC: Everyone (no violence, no mature themes)
- COPPA: No personal data collected, no account required, no social features in MVP — fully compliant by default
- GDPR-K: No data collected; localStorage is purely local, not transmitted

### Regional Differences
None.

---

## 14. Technical Structure

### Engine / Framework
- Vanilla JavaScript, HTML5 Canvas 2D API
- No framework, no build step, single .html file
- RequestAnimationFrame game loop with delta-time capping

### Platforms
- Any modern browser (Chrome, Firefox, Safari, Edge)
- Mobile browsers (iOS Safari, Android Chrome) via touch events
- Fully offline — no network requests

### Save System
- **localStorage only** — saves: `highScore` (int), `muted` (bool), `unlockedSkins` (JSON array)
- No server, no accounts, no cloud sync

### Accounts / Auth
None — guest/anonymous by design.

### Cross-Device Sync
None. Scores are device-local.

### Multiplayer / Netcode
None. Single-player only.

### Anti-Cheat / Server Authority
N/A — single-player, local score, no server. Score manipulation is irrelevant (no stakes).

### Backend Services
None in MVP. Optional Phase 2: leaderboard via REST API.

### Analytics
None in MVP. Optional: simple ping to a free analytics endpoint (Plausible, etc.) without PII.

### Third-Party SDKs
None in MVP.

### App Size
~20–30 KB (single HTML file, no assets). Gzip: ~8–10 KB.

### Performance Notes
- Target 60fps on mid-range mobile (iPhone SE 2nd gen, Android with Chrome)
- Canvas cleared and fully redrawn each frame (no dirty-rect optimization needed at this scale)
- Particle count capped at 20 to avoid mobile slowdown
- requestAnimationFrame delta capped at 50ms to prevent spiral of death on tab switch

---

## 15. Pacing & Difficulty

### Early Game (Score 0–5)
- Moderate pipe speed (~180px/s) [Estimated]
- Large gap (~160px) — very forgiving
- Player learns flap rhythm; most first-time players die within first 3 pipes
- Goal: survive long enough to feel the "click" of the mechanic

### Mid Game (Score 5–20)
- Speed ramps to ~220px/s across 4 ticks [Estimated]
- Gap narrows to ~130px
- Pipe rhythm becomes more demanding; requires consistent flap timing
- Most casual players plateau here; score 10 feels like a major achievement

### Late Game (Score 20+)
- Speed ~250px/s (cap); gap ~100–110px [Estimated]
- Pixel-perfect timing required; muscle memory dominant
- Very few players reach 40+; score 50 is "legendary" territory
- No new mechanics — pure execution test

### Milestone "Aha" Moments
1. **Score 1:** "I can do this!" — first pipe passed
2. **Score 5:** Speed bump felt for first time — difficulty spike awareness
3. **Score 10:** Mid-game plateau — achievement unlock reinforces progress
4. **Score 25:** Late-game entry — player is now skilled

### Churn Points [Estimated]
- **Score 0–3:** ~40% of players quit here (can't learn flap timing)
- **Score 3–10:** ~35% plateau (timing inconsistency)
- **Score 10–25:** ~20% plateau (speed wall)
- **Score 25+:** ~5% reach this range (hardcore)

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---|---|---|
| Gravity + flap physics | ✅ | ✅ |
| Scrolling pipe pairs with random gaps | ✅ | ✅ |
| Collision detection (pipes + ground + ceiling) | ✅ | ✅ |
| Score counter + best score persistence | ✅ | ✅ |
| Start screen + game-over screen | ✅ | ✅ |
| Mobile touch + desktop keyboard/click | ✅ | ✅ |
| Responsive canvas | ✅ | ✅ |
| WebAudio SFX (flap/score/death) | ✅ | ✅ |
| Mute toggle | ✅ | ✅ |
| Particle death effect + screen shake | ✅ | ✅ |
| Subtle difficulty ramp | ✅ | ✅ |
| Cosmetic bird skins | ❌ | ✅ |
| Achievement system | ❌ | ✅ |
| Daily challenge mode | ❌ | ✅ |
| Online leaderboard | ❌ | ✅ |
| Social share button | ❌ | ✅ |
| "Watch ad to continue" | ❌ | ✅ |

### Phased Roadmap

**Phase 1 — Playable Core (Day 1)**
- HTML5 Canvas setup, rAF loop, delta time
- Bird: gravity, flap impulse, rotation, draw
- Pipes: spawn, scroll, gap randomization, despawn
- Collision: AABB with inset, game-over trigger
- Score: increment on pipe pass, localStorage best
- Screens: start, gameplay HUD, game-over
- Controls: touch, click, Space, Up arrow

**Phase 2 — Juice & Polish (Day 1–2)**
- WebAudio SFX (flap, score, death, ambient)
- Mute toggle + localStorage persist
- Screen shake on death
- Particle burst on death
- Score pop (+1 float animation)
- Bird rotation following velocity
- Responsive canvas scaling / resize handler
- Difficulty ramp (speed + gap)

**Phase 3 — Retention Features (Day 2–3)**
- Achievement system (4 milestones, localStorage)
- Cosmetic skins (4, localStorage unlock)
- Daily challenge mode (date-seeded RNG)
- Social share button

**Phase 4 — Monetization (Optional, Day 3+)**
- Ad placeholder integration (banner + interstitial + rewarded)
- GDPR consent banner
- Online leaderboard API

### Recommended Tech Stack
- Vanilla JS + HTML5 Canvas — no framework, no bundler
- localStorage for persistence
- Web Audio API for SFX
- Single .html file deployment

### Required Asset List
All assets are procedurally drawn (no files needed):
| Asset | Method |
|---|---|
| Bird | Canvas arcs, ellipses, fills |
| Pipes | Canvas fillRect + rounded caps |
| Background sky | Canvas gradient fill |
| Ground | Canvas fillRect |
| Clouds | Canvas arcs |
| Particles | Canvas small circles |
| SFX | WebAudio oscillator nodes |
| Fonts | System/canvas font stack |

### Hardest Parts / Risks
1. **Collision fairness:** Hitbox must be inset enough to feel fair, not so inset it feels broken. Test on multiple screen sizes.
2. **Mobile performance:** Particle system and canvas redraws must stay 60fps on low-end Android. Cap particles aggressively.
3. **Flap feel:** Getting gravity constant + flap impulse ratio right is the single most important tuning task. Too floaty = boring; too heavy = impossible. Recommend ~0.5 gravity units/frame² and -8 flap impulse (60fps baseline) then tune. [Estimated]
4. **Responsive layout:** Canvas must scale correctly on iPhone SE (375x667) through iPad (768x1024) without distortion.
5. **Touch responsiveness:** touchstart (not touchend) must trigger flap to avoid perceived lag on mobile.

---

## 17. Open Questions

1. **Optimal difficulty ramp values:** Speed increase per milestone and gap decrease amounts are estimated. Requires 20–30 playtests with a range of skill levels to tune properly. The values in this blueprint are a reasonable starting point.
2. **Ambient audio loop:** Whether a background music loop improves or hurts the experience is subjective. Recommend A/B testing muted-default vs music-default with real users.
3. **Continue mechanic (rewarded ad):** Should the "watch ad to continue" button give invincibility, slow-mo, or teleport-past-current-pipe? Each has different feel. Invincibility for 3 seconds is simplest.
4. **Skin unlock criteria:** Score milestones vs collected feathers vs watch-ad-to-unlock — the unlock trigger affects monetization strategy if ads are added.
