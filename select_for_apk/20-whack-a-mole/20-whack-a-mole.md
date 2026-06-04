# Whack-a-Mole — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Whack-a-Mole is the definitive reflex arcade game: moles pop up from holes in a grid and the player taps or clicks them before they duck back down. The game is defined by escalating tempo — early rounds are leisurely; late rounds become frantic. [Confirmed] Originally created by Bob Patton and manufactured by Bob's Space Racers in 1976, the franchise was later acquired by Mattel/Hasbro. [Confirmed] The arcade cabinet typically features five holes and a foam mallet; digital adaptations expand to 3×3 or 4×4 grids and replace the mallet with tap/click input.

**Quick facts:**
- **Original Developer:** Bob's Space Racers / Hasbro (arcade); dozens of indie clones exist on web and mobile
- **Platforms:** Arcade cabinet [Confirmed]; HTML5/web, iOS, Android (clones) [Confirmed]
- **Session Length:** ~60 seconds per round [Confirmed]
- **Age/Content Rating:** E for Everyone (ESRB) [Confirmed] — suitable for all ages, no COPPA/GDPR-K concerns for this mechanic-only clone unless collecting user data
- **Monetization Model (clone target):** Free-to-play, no IAP — local high score via localStorage; optional ad integration if desired
- **Genre:** Arcade reflex / reaction-speed

---

## 2. Core Loops

- **30-second loop:** A mole (or bomb, or golden mole) pops up in one of nine holes. Player taps/clicks it within the visible window (1.5 s early → 0.4 s late) for points. A whack plays a squash animation + sound + floating score. Miss = no penalty. Hit bomb = score penalty + time penalty. Mole hides; another pops elsewhere. Repeat rapidly.
- **Session loop:** Player starts a 60-second timed round. Pop rate and speed escalate continuously. Player aims to maximize score via hits and combos. Round ends → end screen shows final score vs. personal best → prompt to replay.
- **Meta loop:** Personal best (high score) saved to localStorage. Player returns to beat their own record. No multi-session progression beyond the leaderboard score — pure arcade mastery loop.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
| Mechanic | Detail |
|---|---|
| Mole pop-up | A mole appears in a random unoccupied hole; it is visible for a shrinking window (see Pacing section) |
| Whack | Player clicks/taps a visible mole; triggers score, animation, and sound; mole instantly hides |
| Miss | Mole hides on its own after its window expires; no score, combo resets |
| Bomb | Red bomb appears instead of mole; hitting it deducts 20 pts and 2 s from clock; missing it is safe |
| Golden Mole | Rare variant worth 50 pts (vs. 10 for normal); +2 s time bonus |
| Combo | Consecutive hits without a miss multiply points (×1 → ×2 → ×3 → ×4 max) |
| Speed scaling | Every 10 seconds the spawn interval decreases and mole visibility window shrinks |
| Timer | 60-second countdown; bombs can subtract time; golden moles can add time |
| High score | Best score saved locally; shown in HUD and end screen |

### Core Verbs
- **Tap / Click** — the single primary input
- **Watch** — visually track holes for activity

### Game Modes
| Mode | Description |
|---|---|
| Classic (main) | 60-second timed round, escalating speed, bombs and golden moles included |
| (Optional) Endless | No timer; moles speed up until player misses 5 in a row — score-only challenge [Estimated] |

### Input Scheme
- **Desktop:** Mouse click on mole sprite
- **Mobile:** Touch tap on mole sprite (large hit targets, min 80×80 px)
- **Orientation:** Portrait preferred on mobile; landscape functional on desktop
- **No drag, no swipe, no keyboard** — single-action input model

### Win / Lose / Fail Conditions
- **Round end:** Timer hits 0:00 → game stops all spawns → end screen with final score
- **No lives / health system** — player always completes the full 60-second round
- **Bomb hit:** Immediate score and time penalty, but round continues
- **Failure handling:** Timer depletion is the only terminal state; misses reduce combo but not clock

### Difficulty Modes [Estimated]
| Phase | Time Elapsed | Mole Window | Spawn Interval | Active Moles Max |
|---|---|---|---|---|
| Easy | 0–15 s | 1.5 s | 1.8 s | 1 |
| Medium | 15–30 s | 1.1 s | 1.3 s | 2 |
| Hard | 30–45 s | 0.8 s | 0.9 s | 2–3 |
| Insane | 45–60 s | 0.5 s | 0.6 s | 3 |

### Feedback Systems
- **Visual:** Squash/stretch animation on hit; mole "splat" pose; floating score number (+10, +50, −20); hole darkens briefly on miss; screen flash on golden mole hit
- **Audio:** Distinct WebAudio SFX — whack thud, bomb explosion, golden chime, miss thud, round-end fanfare
- **Haptic:** Vibration on mobile (navigator.vibrate) on whack and bomb hit [Estimated]

---

## 4. Progression

### In-Round Progression
- Speed phases (4 tiers) advance automatically based on elapsed time [Confirmed pattern, Estimated thresholds]
- Combo multiplier climbs: ×1 (1 hit), ×2 (3 consecutive), ×3 (6 consecutive), ×4 (10 consecutive) [Estimated]
- Combo resets on any miss

### Session-to-Session Progression
- Personal best (high score) stored in localStorage — the only persistent metric
- No level unlock tree, no upgrade system — pure score chase
- Optional: future add-on could include medal tiers (Bronze/Silver/Gold at 100/300/600 pts) [Estimated]

### Gating
- None — full gameplay available from first session; no timewalls or paywalls in the free-play arcade model

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Score (pts) | Session-only, not persistent | Hitting moles / combo multiplier | N/A — display metric only |
| High Score | Persistent (localStorage) | Best round result | N/A |

No soft/hard currency, no premium currency. Pure score-based economy. [Confirmed for arcade genre]

### Scoring Formula [Estimated]
```
hit_points = base_value × combo_multiplier
base_value: Normal mole = 10 pts, Golden mole = 50 pts
bomb_penalty: −20 pts (score floor = 0, cannot go negative)
combo_multiplier: min(floor(consecutive_hits / 3) + 1, 4)
time_bonus_golden: +2 s added to clock
time_penalty_bomb: −2 s removed from clock (floor = 1 s remaining)
```

### Mole Type Spawn Rates [Estimated]
| Type | Spawn Weight | Notes |
|---|---|---|
| Normal Mole | 75% | Standard 10 pt target |
| Golden Mole | 15% | 50 pts, adds time |
| Bomb | 10% | Penalty target, safe to miss |

*Assumption: Weights are applied each time a new mole slot is selected. These can be tuned for balance.*

### RNG
- Mole position: uniform random among unoccupied holes
- Mole type: weighted random per table above
- No gacha, no loot tables, no drop rates beyond spawn weights

---

## 6. Content Inventory *(counts + lists)*

### Grid & Holes
- Default: 3×3 = 9 holes [Confirmed as standard for digital Whack-a-Mole]
- Optional 4×4 = 16 holes for "Pro" mode [Estimated]

### Mole Types: 3
1. Normal Mole — brown, standard
2. Golden Mole — yellow/gold, rare bonus
3. Bomb — red/black, penalty

### Animations per Entity
- Pop-up (rise from hole)
- Idle (bobbing while visible)
- Whacked (squash + eyes ×)
- Duck-back (retreat if missed)

### Audio Cues: 6
1. Whack thud (normal hit)
2. Golden chime (golden hit)
3. Bomb explosion (bomb hit)
4. Miss thud (mole retreats unwhacked)
5. Combo milestone chime
6. Round-end fanfare

### Screens: 4
1. Start / Title
2. Gameplay
3. End / Results
4. (Embedded) Settings panel / mute toggle

---

## 7. Theme, Narrative & Tone

- **Setting:** Grassy meadow or carnival fairground; holes in a wooden or dirt surface [Confirmed aesthetic from franchise]
- **Premise:** No story — pure arcade action. The player is the "whacker" and moles are the targets.
- **Characters:** Cute cartoon moles (brown, big eyes, buck teeth); golden variant; comedic bomb character
- **Tone:** Light, fun, comedic — suitable for all ages. Moles are cartoonishly squashed, not harmed. Visual style is bright and cheerful.
- **Dialogue/Text:** Minimal — round-start countdown "3-2-1-GO!", combo announcements ("COMBO x3!"), end-screen congratulations/encouragement
- **IP:** Original characters — do not use Hasbro's Whac-A-Mole name, mole designs, or trade dress

---

## 8. Meta & Social Systems

- **High Score Persistence:** localStorage only — no server-side leaderboard in MVP [Estimated]
- **Achievements:** Optional — "First 100 pts", "Combo x4 achieved", "Bomb survivor" (hit no bombs in a round) [Estimated]
- **Daily/Weekly Missions:** None in MVP arcade model [Estimated]
- **Events:** None — pure arcade
- **Multiplayer:** None in MVP; optional async leaderboard via backend in a future version
- **Sharing:** Optional "Share your score" social button (navigator.share API) [Estimated]
- **Live-ops:** None — static game, no content pipeline needed

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Start / Title Screen | Game branding, how-to-play summary, high score display, "PLAY" button |
| Gameplay Screen | Active game: 3×3 grid, HUD, moles popping |
| End / Results Screen | Final score, high score, grade/rating, "PLAY AGAIN" + "HOME" buttons |
| Settings Panel (overlay) | Mute toggle; accessible from gameplay HUD via gear icon |

### Settings / Options Menu Contents
- Sound on/off toggle (mutes all WebAudio)
- (Optional) Haptic feedback on/off
- Credits / version number

### Gameplay HUD Elements
| Element | Position | Detail |
|---|---|---|
| Score | Top-left | Current round score, large font |
| Best | Top-right | Personal best from localStorage |
| Timer | Top-center | Countdown MM:SS, turns red at ≤10 s |
| Combo | Below timer | "COMBO ×N" badge, animates on increment |
| Mute button | Bottom-right or top-right corner | Speaker icon |

### Navigation Flow
```
Start Screen → [PLAY] → Gameplay Screen
Gameplay Screen → [timer = 0] → End Screen
End Screen → [PLAY AGAIN] → Gameplay Screen (reset)
End Screen → [HOME] → Start Screen
Gameplay Screen → [gear icon] → Settings Panel (overlay, game pauses)
```

### Onboarding / Tutorial (First-Time User Flow)
1. Start screen displays a 3-step tooltip: "Moles pop up → Tap them fast → Avoid bombs!"
2. A brief 2-second animated preview shows a mole popping and being whacked before the PLAY button activates
3. First round: moles pop slowly (easy phase) giving the player time to learn
4. On first golden mole hit: floating label "+50 BONUS TIME!" clarifies the mechanic
5. On first bomb hit: floating label "BOMB! −20 pts −2s" clarifies penalty
6. No formal tutorial mode needed — mechanics are self-evident at slow speed

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D** flat/cartoon style
- **Camera:** Fixed top-down perspective on the hole grid; no scrolling or camera movement
- **Orientation:** Portrait primary (mobile); responsive layout for landscape/desktop

### Art Style
- Flat cartoon with bold outlines
- Color palette: warm greens (grass), browns (dirt/holes), bright yellow (golden mole), red (bomb), white (UI)
- Moles: round, expressive faces — big eyes, small snouts, buck teeth; pure CSS + emoji or Canvas-drawn
- Holes: dark oval/circle with inner shadow giving depth
- Background: simple gradient sky + grass strip

### Animation Set [Estimated]
| Entity | Animation | Duration |
|---|---|---|
| Mole pop-up | Scale Y 0→1 from hole bottom | 150 ms |
| Mole idle | Gentle bob (translate Y ±3 px) | Loop |
| Mole whacked | ScaleX 1.4, ScaleY 0.4, then hide | 200 ms |
| Mole duck-back | Scale Y 1→0 downward | 200 ms |
| Score float | Opacity 1→0, translate Y −40 px | 600 ms |
| Combo badge | Scale 0.8→1.2→1.0 bounce | 300 ms |
| Timer red pulse | Color pulse when ≤10 s | 1 s loop |

### VFX
- Screen shake on bomb hit (translate ±4 px, 3 cycles, 150 ms)
- Particle burst (4–6 stars) on golden mole hit
- Hole glow on recent activity

### Audio (WebAudio API, generated SFX — no external files)
| Sound | Type | Description |
|---|---|---|
| Whack | Impact | Short low-freq thump (oscillator burst, 80 Hz, 100 ms) |
| Golden | Chime | Rising two-tone ping (440 Hz → 880 Hz) |
| Bomb | Explosion | Noise burst + pitch drop (200→50 Hz, 200 ms) |
| Miss | Soft thud | Quiet mid-freq bump (200 Hz, 80 ms) |
| Combo | Ascending ding | 3-note scale (C4-E4-G4) |
| Round end | Fanfare | 5-note ascending melody |

---

## 11. Monetization

**MVP Clone Target: No monetization.** This is a free arcade HTML5 game for personal/portfolio use.

If ads are added to a distributed version:
| Ad Type | Placement | Frequency |
|---|---|---|
| Rewarded Video | End screen: "Watch ad for +15 s in next round" | Optional, on demand |
| Interstitial | After every 3rd round | Max 1 per 3 sessions |

### IAP Table (if building a hosted/store version)
| Product | Price | Contents |
|---|---|---|
| Remove Ads | $1.99 | No interstitials |
| Theme Pack | $0.99 | Alternate mole skins (space, underwater) |

**No loot boxes, no gacha, no random-reward IAP.**

### Consent / Privacy
- No user data collected in MVP localStorage-only build
- If ads added: implement IAB CMP (GDPR consent banner) before ad SDK init
- iOS: ATT prompt required before any IDFA-based tracking
- Age gate: not required for this mechanic content (rated E), but recommended if targeting under-13 audience with ads

---

## 12. Retention Hooks

- **High Score Chase:** Personal best is always visible on start screen — primary driver to replay [Confirmed as core loop of arcade genre]
- **Short Session:** 60-second rounds make "one more game" frictionless
- **Combo Mastery:** Combo system rewards skill improvement across sessions
- **No Energy/Lives System:** No artificial gating — play as often as desired [Confirmed for free arcade model]
- **Idle/Offline Earnings:** None — this is an active reflex game, no idle mechanics [Confirmed]
- **Push Notifications:** None in MVP web build
- **FOMO/Urgency:** None beyond the in-round timer countdown
- **Replay Hook:** End screen immediately offers "PLAY AGAIN" with one tap

---

## 13. Localization & Accessibility

### Localization
- **MVP:** English only
- **Localization-ready:** All UI strings should be in a single constants object for easy translation
- **RTL:** Not required for MVP; if added, CSS `direction: rtl` + grid mirroring needed
- **Regional Differences:** None for a free HTML5 game

### Accessibility
- **Touch targets:** Minimum 80×80 px per mole cell (WCAG 2.5.5 target size)
- **Color contrast:** Score/timer text minimum 4.5:1 contrast ratio against background
- **Colorblind:** Mole types distinguished by shape + icon, not color alone (golden = star icon, bomb = skull icon)
- **Text Scaling:** Fluid typography (clamp/vw units); no fixed pixel font sizes
- **Keyboard:** Not applicable (tap/click game) — optional spacebar "random whack" for accessibility [Estimated]
- **Screen Reader:** ARIA live region for score updates [Estimated]
- **Difficulty Assist:** Game naturally eases in (slow start phase) — no explicit assist mode needed

### Age / Content Rating
- ESRB: E (Everyone) [Confirmed for arcade genre]
- PEGI: 3 [Confirmed for arcade genre]
- COPPA: No PII collected in MVP → compliant by default
- GDPR-K: No tracking in MVP → compliant by default

---

## 14. Technical Structure

### Engine & Language
- **Engine:** None — vanilla JavaScript + HTML5 DOM / CSS Animations
- **Rendering:** DOM-based (divs + CSS transforms) for moles and grid; no Canvas required
- **Language:** HTML5 + CSS3 + ES6 JavaScript (no transpilation needed)

### Platform Support
- **Web:** All modern browsers (Chrome, Firefox, Safari, Edge) [Confirmed]
- **Mobile Web:** iOS Safari 14+, Android Chrome 80+ [Estimated]
- **Offline:** Yes — single .html file, zero network dependencies, works offline
- **App Store:** Not targeted in MVP; could be wrapped with Capacitor/Cordova if needed

### Save System
- **localStorage** — key: `whackamole_highscore` — stores best score as integer
- No accounts, no auth, no cloud sync
- No cross-device sync (single-device local storage)

### Multiplayer / Netcode
- **None** — single-player only; anti-cheat N/A

### Performance Notes [Estimated]
- Target 60 FPS; CSS animations are GPU-composited (transform/opacity only)
- DOM element count: ~20 nodes for the game grid — trivially lightweight
- No memory leaks: all setInterval/setTimeout cleared on round end and restart

### SDKs & Dependencies
- **None** in MVP — zero external dependencies, zero network calls
- Analytics: none in MVP; optional gtag.js snippet if hosted

### File Size
- Target: < 50 KB total (single HTML file with inline CSS + JS) [Estimated]

---

## 15. Pacing & Difficulty

### Difficulty Curve (60-second round)
```
0–15 s  [Easy]    Mole window 1.5 s, spawn interval 1.8 s, max 1 mole active
15–30 s [Medium]  Mole window 1.1 s, spawn interval 1.3 s, max 2 moles active
30–45 s [Hard]    Mole window 0.8 s, spawn interval 0.9 s, max 2–3 moles active
45–60 s [Insane]  Mole window 0.5 s, spawn interval 0.6 s, max 3 moles active
```
*All values [Estimated] based on genre norms; tunable in code constants.*

### Milestone "Aha" Moments
1. **0–5 s:** Player hits first mole → immediate reward feeling, combo starts
2. **~20 s:** Speed increase noticed — tension rises
3. **~30 s:** Multiple simultaneous moles appear — requires spatial attention
4. **First golden mole hit:** Dopamine spike from 50-pt reward
5. **First bomb miss (safe):** Player learns avoidance strategy
6. **Round end:** Final score reveal + comparison to high score

### Churn Points [Estimated based on genre reviews]
- Players disengage if bomb penalties feel random/unfair — solution: bombs have a distinct visual (red + skull) with a brief flash-warning before full pop
- Players disengage if moles are too fast too soon — solution: generous 15-second easy phase
- Missing a string of moles in Insane phase is frustrating — solution: combo reset is punishment enough; no further penalty for misses

### Early / Mid / Late Pacing
- **Early (0–15 s):** Tutorial-feel; player builds combo; score climbs steadily; confidence builds
- **Mid (15–45 s):** Core engagement zone; speed challenges skill; bombs and golden moles appear; score multiplied by combos
- **Late (45–60 s):** Frantic sprint; maximum moles and speed; score dramatically influenced by this phase; high-stakes finish

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist
| Feature | MVP | Full |
|---|---|---|
| 3×3 grid, normal moles | ✓ | ✓ |
| 60-second timed round | ✓ | ✓ |
| Speed scaling (4 phases) | ✓ | ✓ |
| Bomb penalty | ✓ | ✓ |
| Golden mole bonus | ✓ | ✓ |
| Combo multiplier | ✓ | ✓ |
| localStorage high score | ✓ | ✓ |
| WebAudio SFX | ✓ | ✓ |
| Mobile responsive / touch | ✓ | ✓ |
| Start + End screens | ✓ | ✓ |
| Mute toggle | ✓ | ✓ |
| Floating score text | ✓ | ✓ |
| Screen shake on bomb | ✓ | ✓ |
| 4×4 Pro grid mode | — | ✓ |
| Multiple themes/skins | — | ✓ |
| Online leaderboard | — | ✓ |
| Achievement system | — | ✓ |
| Share score button | — | ✓ |
| Endless mode | — | ✓ |

### Phased Build Roadmap
**Phase 1 — Core Loop (Day 1–2)**
- HTML skeleton + 3×3 CSS grid
- Mole pop/hide logic (random hole selection, timed visibility)
- Click/tap detection + score increment
- 60-second countdown timer
- Basic end screen

**Phase 2 — Polish & Juice (Day 2–3)**
- Squash animation on whack
- Floating score text
- Bomb + golden mole types with weighted RNG
- Combo multiplier system
- Speed phase escalation
- WebAudio SFX (all 6 sounds)
- localStorage high score persistence
- Mute toggle

**Phase 3 — UX & Mobile (Day 3–4)**
- Responsive grid (CSS clamp + vmin sizing)
- Touch target sizing (min 80×80 px)
- Start screen with how-to-play
- HUD polish (score, timer, combo badge)
- Screen shake on bomb hit
- Accessibility pass (ARIA, contrast, colorblind icons)

**Phase 4 — Full Feature (Optional, Day 5+)**
- 4×4 grid mode
- Theme skins
- Share score button
- Online leaderboard (requires backend)

### Recommended Tech Stack
- HTML5 + CSS3 + Vanilla ES6 JS — zero dependencies
- No build step, no framework, no bundler
- Single `.html` file deployment
- localStorage for persistence
- Web Audio API for sound (no audio files needed)

### Required Asset List
- All assets generated in code (CSS + emoji or Canvas-drawn shapes)
- No external image files
- No external audio files
- Font: system-ui or Google Fonts (optional — if Google Fonts, load inline via @import for offline safety, or use system fonts only)

### Hardest Parts / Risks
1. **Timer cleanup on restart:** All active mole setTimeout IDs must be tracked and cleared — forgetting this causes ghost moles after restart. Mitigate: store all timer IDs in an array; clearAllTimers() on every reset.
2. **Multi-mole collision:** When maxActive > 1, need to prevent the same hole being selected twice simultaneously. Mitigate: track `activeHoles` Set; exclude occupied holes from random selection.
3. **Mobile touch precision:** Small grid cells on small screens cause miss-clicks. Mitigate: oversized hit targets (cell fills entire grid cell), prevent default on touch to stop scroll interference.
4. **Bomb timing fairness:** Bomb appearing and being accidentally tapped immediately feels unfair. Mitigate: bomb has a 200 ms "warning phase" (flash/shake) before becoming a valid hit target.
5. **Audio on mobile:** WebAudio context must be initialized inside a user gesture handler (first tap) — iOS blocks autoplay. Mitigate: resume AudioContext in the PLAY button click handler.

---

## 17. Open Questions

1. **Exact original arcade scoring formula:** The original Hasbro/Bob's Space Racers cabinet scoring details are not fully documented publicly. [Estimated values used throughout; tune via playtesting.]
2. **Optimal bomb frequency:** 10% spawn weight is an estimate — may feel too frequent or too rare depending on target audience. Recommend A/B testing at 5%, 10%, and 15%.
3. **Combo multiplier cap:** Cap of ×4 is estimated; playtesting may show ×3 feels more balanced for a 60-second round.
4. **Pro mode grid size:** Whether a 4×4 vs. 5×5 grid feels better on large screens requires playtesting.
