# GemBlast — Clone Blueprint
*(Original-branded Match-3 Puzzle Game)*

> **Legal note:** This blueprint targets an original-branded clone named "GemBlast."
> Replicate mechanics and systems only — not any original game's name, logo,
> copyrighted art, audio, or proprietary text.

---

## 1. Snapshot

GemBlast is a tile-swap match-3 puzzle game played on a square grid of colored gems.
The player swaps two adjacent gems to line up three or more of the same color; matched
gems clear, remaining gems fall under gravity, new gems drop in from the top, and cascades
chain automatically. Each level sets a target score within a fixed move budget, creating a
pure puzzle challenge with no real-time pressure by default. Special gems spawn from 4- or
5-gem matches, adding explosive power plays and strategic depth.

**Quick facts:**
- **Original inspiration:** Candy Crush Saga — King (2012), now King/Activision Blizzard
- **Platforms targeted for clone:** Web (HTML5), desktop, mobile browser
- **Genre:** Casual puzzle / match-3
- **Session length:** 2–5 minutes per level [Estimated]
- **Age/content rating:** IARC 3+ / Everyone (no violence, no ESRB concern) [Confirmed]
- **Monetization model (original):** F2P, ads + IAP lives/boosters [Confirmed]
- **Clone monetization:** Omitted (pure game); can add later

---

## 2. Core Loops

**30-second loop:**
Scan the board → identify a promising swap → tap/drag a gem to an adjacent gem →
if match: watch clear + cascade → score updates → repeat. If no match: swap reverts.

**Session loop:**
Enter a level → read target score + move count → play until target is hit (win) or
moves run out (lose) → see score/star rating → retry or advance to next level.
One session = 1–3 attempts at 1–3 levels, ~5–10 minutes total.

**Meta loop:**
Progress through a world-map of numbered levels, each slightly harder → unlock new
level types and obstacles as levels increase → chase high scores / 3-star ratings
on completed levels → optionally revisit for better stars. Long-term: complete all
levels in a world to unlock the next world. [Estimated — follows genre standard]

---

## 3. Mechanics, Controls & Game States

### Core Mechanics

| # | Mechanic | Detail |
|---|----------|--------|
| 1 | **Gem swap** | Select a gem, then an adjacent gem (up/down/left/right). Swap executes only if it creates a match of ≥3; otherwise the animation reverses. Diagonal swaps: not allowed. |
| 2 | **Match detection** | After every swap and every cascade step, scan all rows and columns for runs of ≥3 identical gems. Overlapping matches (e.g., cross shape) are all detected in one pass. |
| 3 | **Clear & gravity** | Matched gems are removed. Gems above empty cells fall straight down. New gems drop in from the top to fill the column. |
| 4 | **Cascade** | After refill, run match detection again. Any new matches auto-clear (no move cost). Repeat until no matches remain. |
| 5 | **Special gems** | Spawned in place of a matched gem on 4- or 5-matches (see table below). |
| 6 | **Deadlock / reshuffle** | After every refill, check if any valid swap exists. If none, shuffle the board and re-check (repeat until valid). Display "Reshuffling!" toast. |
| 7 | **No-initial-match gen** | Board generation rejects any layout containing a horizontal or vertical run of ≥3 identical gems before play begins. |
| 8 | **Moves limit** | Each level has a fixed move budget (e.g., 20 moves). Every intentional swap (including invalid ones that revert) costs 0 moves; only a successful swap that triggers matches costs 1 move. [Estimated — Candy Crush: only the swap costs a move, not reverts] |
| 9 | **Target score** | Win condition: reach the target score within the move limit. |
| 10 | **Input lock** | All input is disabled while the clear→fall→refill→cascade animation is playing. |

### Special Gems

| Match shape | Gem created | Effect when matched/activated |
|-------------|-------------|-------------------------------|
| 4 in a line (horizontal) | **Line Gem H** (horizontal stripe) | Clears the entire row |
| 4 in a line (vertical) | **Line Gem V** (vertical stripe) | Clears the entire column |
| 5 in an L or T shape | **Burst Gem** (wrapped/bomb) | Clears 3×3 area centered on gem |
| 5 in a straight line | **Nova Gem** (color bomb) | Clears all gems of the swapped partner's color |

Special gems activate when matched as part of a normal 3+ match, or when swapped directly
with another gem (including with another special). Special + special combos produce escalating
effects [Estimated — follows genre standard].

### Game Modes

| Mode | Description |
|------|-------------|
| **Campaign** | Numbered levels, each with unique target score + move count. Primary mode. |
| **Endless / Zen** | No move limit, no target — pure relaxation mode. Score tracked for leaderboard. [Estimated] |
| *(Daily Challenge)* | One curated level per day with global leaderboard. [Optional / Phase 2] |

### Controls

| Action | Desktop | Mobile |
|--------|---------|--------|
| Select gem | Left-click | Tap |
| Swap | Click adjacent gem, or drag to neighbor | Tap adjacent, or swipe toward neighbor |
| Deselect | Click same gem again or click empty area | Tap same gem or tap empty |

- **Orientation:** Portrait primary; landscape supported with wider board.
- **Highlight:** Selected gem shows glow ring; valid swap targets show subtle pulse.

### Win / Lose / Fail States

- **Win:** Score ≥ target before moves run out → "Level Clear!" overlay with star rating (1–3 stars based on score tier), score breakdown, next-level button.
- **Lose:** Moves = 0 and score < target → "Out of Moves" overlay with final score, best score, replay button.
- **Continued play after target:** Extra moves still play out; extra score counts toward star rating.

### Star Rating [Estimated]

| Stars | Condition |
|-------|-----------|
| ★☆☆ | Score ≥ 100% of target |
| ★★☆ | Score ≥ 150% of target |
| ★★★ | Score ≥ 200% of target |

### Feedback Systems

- **Visual:** Match pop animation (scale-up-then-disappear), cascade flash, gem fall ease-in-out, score delta "+NNN" floating text, selected gem glow pulse, invalid swap shake.
- **Audio:** Distinct SFX per event: swap, match, cascade step 1/2/3/4, special gem spawn, special gem activate, level win, level lose (all procedural WebAudio — no file assets required).
- **Haptic:** Vibrate API on match (mobile); single short pulse.

---

## 4. Progression

### Levels

- **Campaign levels:** 50 starting levels across 5 worlds of 10 levels each. [Estimated]
- **Difficulty curve:** Move count decreases and target score increases as levels advance.
- **Obstacles (mid/late):** Locked gems (need adjacent match to unlock), stone tiles (need ≥2 adjacent matches to clear), ice tiles (one match to thaw), licorice swirl (blocks column). [Estimated — genre standard obstacles]

### Unlock System

| Unlock condition | What unlocks |
|-----------------|--------------|
| Complete level N | Level N+1 |
| Complete all 10 levels in a world | Next world + short cutscene [Estimated] |
| 3-star a level | Cosmetic badge; no hard gate |

### Star / Score Persistence

- Best score and star count per level saved to localStorage.
- "Best" displayed on level-select and HUD.

### Prestige / Rebirth

None. [This is a level-based puzzle game, not a prestige loop.]

### Gating

- Only progression gate: completing the prior level. No energy/lives in MVP build.
- Optional: add a 5-lives system (1 life lost per fail, refill over time) in Phase 3.

---

## 5. Economy & RNG

### Currencies

| Currency | Type | Earned from | Spent on |
|----------|------|-------------|----------|
| **Stars** | Soft / progress | Achieving score tiers per level | Cosmetic unlocks (Phase 2) |
| **Coins** | Soft / optional | Level completion bonus | Extra moves (+5 for 900 coins), boosters |
| *(Gems)* | Premium / optional | IAP or rare daily bonus | Extra lives, rare boosters, level skip |

*MVP build: no currencies needed — pure score/move puzzle with no economy gates.*

### Score Formula [Estimated]

```
Base match score   = gems_matched × 60
Cascade multiplier = 1.0 + (cascade_depth × 0.5)   // depth resets to 0 each swap
Special activation = 120 (line gem), 200 (burst gem), 300 (nova gem)
Remaining moves    = moves_left × 100  (bonus at level end)
```

Example: 3-gem match at cascade depth 2 = 3 × 60 × 2.0 = 360 pts.

### Cost-Scaling (Extra Moves) [Estimated]

| Extra move bundle | Cost (coins) |
|-------------------|-------------|
| +5 moves | 900 |
| +5 moves again | 1 200 |
| +5 moves again | 1 600 (cap) |

*Follows power-of-1.33 per purchase in session.*

### RNG

- **Board generation:** Pure random, rejection-sampling to avoid initial matches.
- **New gem drops:** Uniform random across 6 colors; no weighted drop rates in base game.
- **Special gem placement:** Deterministic — whichever gem in the matching set was tapped/selected first becomes the special.
- **No gacha / loot boxes** in base game.

---

## 6. Content Inventory

### Gem Colors (base game)

6 colors: Ruby Red, Sapphire Blue, Emerald Green, Amber Orange, Amethyst Purple, Pearl White.
[Estimated — 6 colors is genre standard for 8×8 board; 5 colors for easier levels, 7 for harder]

### Special Gems

4 types: Line H, Line V, Burst, Nova (described in §3).

### Board Sizes

| Level tier | Board | Colors |
|------------|-------|--------|
| Levels 1–10 | 7×7 | 5 |
| Levels 11–30 | 8×8 | 6 |
| Levels 31–50 | 8×8 | 6 + obstacles |

### Obstacles [Estimated]

| Obstacle | Layers | Removal method |
|----------|--------|---------------|
| Ice tile | 1 | Adjacent match |
| Locked gem | 1 | Adjacent match (frees the gem underneath) |
| Stone tile | 2 | 2 adjacent matches (one per layer) |
| Licorice swirl | — | Match the gem in that cell |

### Worlds / Themes [Estimated]

| World | Theme | Levels |
|-------|-------|--------|
| 1 | Crystal Cave | 1–10 |
| 2 | Ember Fields | 11–20 |
| 3 | Ocean Depths | 21–30 |
| 4 | Storm Peak | 31–40 |
| 5 | Nova Void | 41–50 |

### Boosters (Phase 2+) [Estimated]

| Booster | Effect | Cost |
|---------|--------|------|
| Hammer | Remove any 1 gem | 300 coins |
| Row Blast | Clear a chosen row | 500 coins |
| Shuffle | Re-randomize board | 200 coins |
| +5 Moves | Add 5 to move count | 900 coins |

---

## 7. Theme, Narrative & Tone

**Setting:** An abstract crystalline gem-world — floating islands of glittering minerals drifting
through a deep-space aurora backdrop. No specific character protagonist; the player IS the gem
sorter / cosmic alchemist.

**Premise:** Ancient gem formations have become disordered. By aligning identical gems, the player
restores harmonic resonance to each crystal cluster. Light narrative flavor only — no cutscenes
in MVP; short world-transition text cards in Phase 2.

**Tone:** Relaxing, satisfying, slightly cosmic. Not cutesy/candy-themed. Skews gem/crystal
aesthetic: cool blues and purples in UI; gems have subtle inner glow rather than cartoon outlines.

**Writing style:** Minimal text; short celebratory phrases ("Brilliant!", "Cascade!", "GemBlast!!")
on win overlays. No dialogue. No IP borrowed.

**Color palette:**
- Background: deep navy `#0d1b3e` to `#1a2a5e` gradient
- Grid cells: `#1e3060` with subtle border `#2a4080`
- Gem colors: saturated jewel tones (see §6)
- UI accent: gold `#f0c040`
- Text: white `#ffffff`, secondary `#aabcdd`

---

## 8. Meta & Social Systems

### Daily / Weekly Missions [Estimated, Phase 2]

| Mission type | Example | Reward |
|-------------|---------|--------|
| Daily | "Make 5 cascades today" | 50 coins |
| Weekly | "Clear 3 levels with 3 stars" | 200 coins |
| Achievement | "Activate 10 Nova Gems" | Badge + 100 coins |

### Leaderboard [Estimated, Phase 2]

- Per-level high score board (top 10 globally + personal best).
- Implemented via a lightweight backend (Firebase Realtime DB or similar).

### Social

- No multiplayer in MVP.
- "Share score" button generates a PNG snapshot for social media (Phase 2).
- No guilds, co-op, or PvP in scope.

### Live-Ops

- MVP: static content, no live-ops overhead.
- Phase 2: seasonal gem skin packs (cosmetic only), monthly new 10-level world.
- **Live-ops cost estimate:** ~4 dev-hours per new world; skins are CSS palette swaps. Low overhead.

### Battle Pass / Events

None in MVP. Optional limited-time events (e.g., "Meteor Shower Week" with bonus cascade score)
in Phase 3.

---

## 9. UI / UX & Screen Map

### Screen List

| Screen | Purpose |
|--------|---------|
| **Splash / Loading** | Brand logo, version, loading indicator |
| **Start / Home** | Game title, Play button, Settings, Best Score display |
| **Level Select** | World map → scrollable level grid; each node shows stars earned |
| **Pre-Level** | Level number, target score, move count, available boosters, Start button |
| **Gameplay** | Main match-3 board + HUD |
| **Pause** | Pause overlay: resume, restart, settings, exit to menu |
| **Win Overlay** | Stars earned, score, next level / replay / menu |
| **Lose Overlay** | "Out of Moves", final score, extra-moves offer, replay, menu |
| **Settings** | Sound on/off, music on/off, haptics on/off, reset progress |
| **How to Play** | Rules illustrated in 3–4 panels; accessible from Start screen |
| *(Shop — Phase 2)* | Coin packs, booster bundles |
| *(Achievements — Phase 2)* | Badge list, progress bars |

### Settings Menu Contents

- Sound FX: toggle (default on)
- Music: toggle (default on)
- Haptic feedback: toggle (default on)
- Show hints: toggle (highlights a valid swap after 5s idle) [Estimated]
- Reset all progress: confirmation dialog
- Version + credits

### Gameplay HUD

```
┌─────────────────────────────────────┐
│  SCORE: 2 340    TARGET: 5 000      │
│  MOVES: 14 ████████████░░ BEST: 8K  │
│  [🔇]                         [II]  │
├─────────────────────────────────────┤
│                                     │
│           8 × 8  BOARD              │
│                                     │
└─────────────────────────────────────┘
```

Elements: current score, target score, moves remaining (with progress bar), best score,
mute toggle, pause button. Selected gem highlighted. Score delta floating text on match.

### Navigation Flow

```
Splash → Start Screen → Level Select → Pre-Level → Gameplay
                                                  ↘ Pause → Resume / Menu
                                                  ↘ Win → Next Level / Level Select
                                                  ↘ Lose → Replay / Level Select
```

### Onboarding / Tutorial (First-Time User Flow)

1. **Welcome panel:** "Match 3 or more gems to score points!"
2. **Arrow overlay:** Arrow pointing at two specific swappable gems on a pre-seeded board.
3. **Forced swap:** Input locked to only allow the indicated swap; player taps/drags it.
4. **Match fires:** Watch the cascade automatically. "Great! Gems fall and new ones drop in!"
5. **Special gem intro (move 3):** Arrow points to 4-in-a-line opportunity. "Match 4 for a Line Gem!"
6. **Player executes swap:** Special gem spawns. "Tap the Line Gem to activate it!"
7. **Player taps special:** Row clears with big SFX. "Powerful! Now reach the target score."
8. **Tutorial ends:** HUD fades in, normal play resumes. Skip button available from step 1.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera

- **Dimension:** 2D (flat orthographic)
- **Camera:** Fixed top-down view of the grid; no scroll during gameplay
- **Orientation:** Portrait primary (grid centered, HUD above); landscape adapts with wider margins

### Art Style

- **Style:** Flat with jewel-glow inner lighting. Gems are rounded polygons or circles with a radial
  gradient and a bright specular highlight. No cartoon outlines. Minimalist UI chrome.
- **Gem visual design:** Each color has a base hue + 20% lighter center glow + 15% shadow at edge.
  Shape: regular hexagon or rounded square (consistent per build).
- **Grid:** Semi-transparent dark cells; thin bright border; slight perspective shadow under board.
- **Background:** Animated slow-scrolling star field or twinkling nebula (CSS animation, no images).
- **Typography:** Clean sans-serif (system font stack); bold numerals for score; no external fonts needed.

### Color Palette

| Gem | Hex | Name |
|-----|-----|------|
| Red | `#e03040` | Ruby |
| Blue | `#2060e0` | Sapphire |
| Green | `#20b040` | Emerald |
| Orange | `#e08020` | Amber |
| Purple | `#9030c0` | Amethyst |
| White | `#c0d0e8` | Pearl |
| *(7th — hard levels)* | `#e0c020` | Citrine |

Special gems have a swirling inner animation (CSS keyframe rotation of a gradient).

### Animation

| Event | Animation |
|-------|-----------|
| Match clear | Scale 0→1.2→0, opacity fade, 180ms |
| Gem fall | Ease-in-out drop, 200ms per cell, staggered |
| New gem drop-in | Bounce ease, 150ms |
| Swap | Smooth translate, 120ms; reverse 120ms if invalid |
| Selected gem | Pulsing glow ring, 0.8s cycle |
| Cascade bonus | "+Cascade!" text rising from board center |
| Score delta | "+NNN" floating text, rise + fade, 600ms |
| Win | Confetti particle burst (CSS), board glow |
| Lose | Board darkens, shake, overlay slides in |

### Audio (Procedural WebAudio — no external files)

| SFX | Technique |
|-----|-----------|
| Swap | Short "tick" — brief sine tone at 440 Hz, 80ms |
| Match 3 | Chord: 3 stacked sine tones, quick decay 200ms |
| Cascade +1 | Pitch shift +100 cents per level, same chord |
| Special spawn | Rising sweep oscillator 300→900 Hz, 250ms |
| Special activate | Reverb burst + low thump, 400ms |
| Win fanfare | Ascending 4-note arpeggio, 800ms |
| Lose | Descending 3-note, 600ms |
| Reshuffle | Whoosh: filtered noise, 500ms |

- Background music: simple generative ambient drone (optional, mutable).
- All audio gated by user gesture (Web Audio API autoplay policy compliance).
- Mute toggle persists to localStorage.

### Juice Summary

- Input feels instant (swap starts on pointerdown/touchstart, not release).
- Cascade chains feel escalating (SFX pitch rises, score delta text grows).
- Idle hint: after 5s without a move, a valid pair gently pulses.
- All animations respect `prefers-reduced-motion` (disable particle/shake effects if set).

---

## 11. Monetization

*Note: The HTML5 clone MVP is ad-free and IAP-free — a pure game. The monetization
section documents what the original uses and what to add if this game is ever monetized.*

### Original Candy Crush Monetization [Confirmed]

| Type | Placement | Frequency |
|------|-----------|-----------|
| Interstitial video | After every 2–3 level fails | ~30–60s skip |
| Rewarded video | Offered at lose screen for +5 moves | Optional, player-initiated |
| Banner | Bottom of level-select map | Persistent |
| IAP: Lives | 5 lives for $0.99 | On lose with 0 lives |
| IAP: Move bundle | +5 moves for $0.99 | On lose overlay |
| IAP: Booster pack | $1.99–$4.99 | Shop screen |
| IAP: Gold bars | $1.99–$19.99 currency pack | Shop |
| IAP: Remove ads | ~$9.99/year subscription | Settings |

### For Clone (If Monetized) [Estimated]

- Add rewarded video for +3 moves at lose screen (no other ads in MVP).
- GDPR/CMP consent popup required before any ad SDK init (EU traffic).
- iOS ATT prompt required before any cross-app tracking.
- No age-gate needed (IARC 3+), but do not collect personal data from users.

### Loot Boxes / Gacha

None in base game. [Confirmed — Candy Crush does not have gacha]

### Aggressiveness

Candy Crush original: Moderately aggressive (lives timer is main pressure valve; rewarded ads
are soft-ask only; hard IAP push comes only after repeated fails on a single level).
Clone MVP: Non-aggressive (no ads, no IAP, no lives).

---

## 12. Retention Hooks

### Daily Rewards [Estimated, Phase 2]

- Day-1: 30 coins, Day-2: 50, Day-3: 80, Day-4: 100, Day-5: 150, Day-6: 200, Day-7: 300 + booster.
- Streak reset on miss; 7-day cycle repeats.

### Offline / Idle Earnings

**None** — GemBlast is an active puzzle game; there is no offline earning mechanic.
Lives (if added in Phase 3): refill at 1 life per 30 minutes while offline, max 5 lives.

### Push Notifications [Estimated, Phase 2]

| Trigger | Message |
|---------|---------|
| 30 min inactive | "Your gems are waiting! Can you beat your best?" |
| Daily challenge available | "Today's Daily Challenge is live!" |
| Lives full | "You have full lives — time to play!" |

### Energy / FOMO

- MVP: No energy/lives system. Play freely.
- Phase 3: Optional 5-lives system (FOMO for continued play after consecutive fails).
- Time-limited events (Phase 3): countdown timer visible on world map creates urgency.

---

## 13. Localization & Accessibility

### Localization

| Language | Priority |
|----------|---------|
| English | Launch |
| Spanish | Phase 2 |
| French | Phase 2 |
| Brazilian Portuguese | Phase 2 |
| German | Phase 2 |
| Japanese | Phase 3 |
| Korean | Phase 3 |
| Arabic (RTL) | Phase 3 |

- UI strings externalized to a JS locale object from day 1 to ease translation.
- RTL layout supported via CSS `direction: rtl` and logical properties.
- No right-to-left text needed at launch (English only).

### Accessibility

| Feature | Implementation |
|---------|---------------|
| Colorblind mode | Add distinct shape/symbol overlay on each gem color (e.g., ●▲■◆★✦) |
| Text scaling | UI uses `em`/`rem`; respects browser font-size zoom |
| Reduced motion | `@media (prefers-reduced-motion: reduce)` disables particles + shake |
| High contrast | Gems already have high-saturation colors; shapes add non-color distinction |
| Touch target size | All interactive gems ≥ 44×44 CSS px (Apple HIG minimum) |
| Screen reader | `aria-label` on HUD elements; gameplay board is Canvas/visual only (no SR nav needed) |

### Age / Content Rating

- **IARC:** 3+ (Everyone) — no violence, no inappropriate content [Confirmed for genre]
- **COPPA:** If users under 13 are targeted, disable all behavioral advertising and analytics.
  Clone MVP collects only localStorage (no PII, no server, COPPA-safe).
- **GDPR-K:** No personal data collected in MVP; GDPR consent popup needed only if ads are added.

### Regional Differences

None significant for MVP. If monetized: currency localization (price tiers per region),
App Store pricing matrix, regional ad network availability.

---

## 14. Technical Structure

### Engine & Language

- **Clone:** Vanilla HTML5 + CSS3 + JavaScript (ES2020). No framework, no build step.
- **Rendering:** DOM-based (CSS transforms) for MVP; Canvas 2D optional for Phase 2 performance.
- **Original:** King's proprietary engine (C++/Flash legacy → HTML5/native). [Confirmed via public info]

### Platforms

- Web (all modern browsers, mobile Safari / Chrome / Firefox / Edge)
- PWA-installable (add manifest + service worker in Phase 2 for offline + icon)

### Save System

- **localStorage** keys:
  - `gemblast_best` — all-time best score (integer)
  - `gemblast_level` — highest unlocked level
  - `gemblast_stars` — object: `{level: stars}` for all completed levels
  - `gemblast_muted` — boolean audio mute preference
- No cloud save, no accounts in MVP.
- Phase 2: optional Firebase Auth (anonymous) + Firestore for cross-device sync.

### Online vs Offline

- MVP: fully offline. All assets inline in one HTML file.
- No network calls in MVP.

### Multiplayer / Netcode

None. Single-player only. Anti-cheat: N/A.

### Backend Services (Phase 2+)

| Service | Purpose |
|---------|---------|
| Firebase Auth (anonymous) | Guest account for cross-device |
| Firestore | Score/progress sync |
| Firebase Analytics | Funnel + level drop-off |
| Remote Config | Tune move counts + score targets without redeploy |
| AdMob (if monetized) | Ad mediation |

### SDKs (MVP)

None — zero external dependencies. File is self-contained.

### Performance Notes

- 8×8 = 64 DOM elements; trivially fast on any modern device.
- Animation via CSS transitions + `requestAnimationFrame` for JS-driven sequences.
- No images, no audio files. All visuals: CSS + SVG inline. All audio: WebAudio API.
- Target: 60fps on a 2019 mid-range Android phone.

### App Size

- MVP HTML file: ~30–60 KB unminified [Estimated]

---

## 15. Pacing & Difficulty

### Early Game (Levels 1–10)

- Large move budgets (25–30 moves), low target scores, 7×7 board, 5 colors.
- No obstacles. Special gems introduced organically by level 5.
- Goal: teach swap, cascade, and special gem mechanics.
- "Aha" moment: first cascade chain (usually level 2–3).

### Mid Game (Levels 11–30)

- Tighter move budgets (18–22 moves), higher targets, 8×8 board, 6 colors.
- Obstacles introduced one type per world (ice → locked gem → stone).
- Special gem combos (line+burst, burst+nova) unlock significant power plays.
- "Aha" moment: first nova gem wipes the board; player understands planning depth.

### Late Game (Levels 31–50)

- Brutal efficiency required (14–18 moves), very high targets.
- Mixed obstacles + 6 colors = board feels dense.
- Only valid-path solutions win; casual matching fails.
- Churn point: levels 35–40 are historically where players retry most (known genre pattern). [Estimated]

### Difficulty Levers [Estimated tuning]

| Lever | Early | Mid | Late |
|-------|-------|-----|------|
| Move count | 28 | 20 | 15 |
| Target score | 3 000 | 8 000 | 18 000 |
| Colors on board | 5 | 6 | 6 |
| Obstacle types | 0 | 1–2 | 2–3 |
| Board size | 7×7 | 8×8 | 8×8 |

### Churn Prevention

- After 3 consecutive fails on same level: show a "Tip" modal with a hint about the level.
- After 5 fails: offer a booster (Phase 2) or a difficulty assist (reduce target by 10%).
- Reshuffle animation reassures player that dead boards get fresh chances.

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Phase 2 | Phase 3 |
|---------|-----|---------|---------|
| 8×8 board, 6 colors | ✓ | | |
| Swap + match detection (H+V) | ✓ | | |
| Gravity + cascade loop | ✓ | | |
| No-initial-match generation | ✓ | | |
| Deadlock reshuffle | ✓ | | |
| Special gems (Line H/V, Burst, Nova) | ✓ | | |
| Score formula + cascade multiplier | ✓ | | |
| Moves limit + target score win/lose | ✓ | | |
| Input lock during animation | ✓ | | |
| Start screen + how-to-play | ✓ | | |
| Win / lose overlays + replay | ✓ | | |
| Best score localStorage | ✓ | | |
| Responsive / mobile-first | ✓ | | |
| Click + drag/swipe controls | ✓ | | |
| Procedural WebAudio SFX | ✓ | | |
| Mute toggle | ✓ | | |
| Colorblind mode (symbols) | | ✓ | |
| Multi-level campaign (50 levels) | | ✓ | |
| Level select / world map | | ✓ | |
| Obstacles (ice, locked, stone) | | ✓ | |
| Daily challenge | | ✓ | |
| Leaderboard (Firebase) | | ✓ | |
| PWA install | | ✓ | |
| Lives system | | | ✓ |
| Booster shop | | | ✓ |
| Multiplayer async | | | ✓ |

### Phased Roadmap

**Phase 1 — MVP (1–2 weeks solo dev)**
1. Board data structure (2D array, color enum)
2. Random board generation with no-initial-match validation
3. Swap logic + match detection (horizontal + vertical)
4. Clear + gravity + refill loop
5. Cascade loop + cascade multiplier
6. Deadlock detection + reshuffle
7. Special gem spawn (4-in-line → Line, L/T → Burst, 5-straight → Nova)
8. Special gem activation effects
9. Moves counter + score counter + target win/lose check
10. Input lock during resolve phase
11. UI: HUD, start screen, win/lose overlays, replay
12. Responsive layout + touch/click controls (tap-select + drag-swap)
13. Procedural WebAudio SFX
14. localStorage best score
15. Polish: animations, floating score text, selected-gem highlight

**Phase 2 — Content & Polish (2–3 weeks)**
1. 50 campaign levels (data table: moves, target, board size, colors, obstacles)
2. Level select world map
3. Obstacle tiles (ice, locked gems, stone)
4. Colorblind mode
5. Daily challenge (seeded RNG by date)
6. Firebase integration (scores, progress sync)
7. PWA manifest + service worker

**Phase 3 — Monetization & Retention (3–4 weeks)**
1. Lives system + replenishment timer
2. Booster shop (coin economy)
3. Daily login rewards
4. Push notifications (PWA)
5. Ads integration (AdMob or similar) + GDPR CMP
6. Seasonal events framework

### Recommended Tech Stack

| Layer | Choice | Rationale |
|-------|--------|-----------|
| Rendering (MVP) | DOM + CSS | Zero deps, fast for 64 tiles, easy to animate |
| Rendering (Phase 2) | Canvas 2D or PixiJS | Better particle effects, smoother at scale |
| Logic | Vanilla JS (ES2020) | No build step; ships as one file |
| State | Plain JS object | No Redux needed at this complexity |
| Persistence | localStorage | Offline, no backend needed for MVP |
| Backend | Firebase (Firestore + Auth) | Free tier sufficient; easy setup |
| CI/CD | GitHub Pages | One-command deploy of HTML file |

### Required Asset List

- No external image assets (MVP — all CSS/Canvas drawn)
- No audio files (MVP — all WebAudio procedural)
- Locale strings file (JS object) — 1 file
- Level data table (JSON or JS array) — 1 file (Phase 2)
- Icon + splash image for PWA (Phase 2) — 2 PNG files

### Hardest Parts / Risks

1. **Match detection correctness** — hardest algorithmic piece; most bugs are here.
   Specifically: detecting overlapping L/T shapes for Burst gem spawn, and correctly
   handling chain cascades without re-counting gems cleared in prior steps.
2. **Input lock discipline** — failing to lock input during animation causes double-swaps
   and state corruption. Must use an `isAnimating` flag and queue or discard input.
3. **Deadlock detection performance** — brute-force O(2 × W × H) swap check is fine for
   8×8 but must not run on every frame; run once after each refill settles.
4. **Special gem combo interactions** — Line+Nova, Burst+Nova combos can cascade infinitely
   if not handled with a "already activated" flag per resolve pass.
5. **Touch/drag on mobile** — `touchstart/touchmove/touchend` must preventDefault to avoid
   scroll interference; use pointer events API for unified handling.
6. **No-initial-match generation edge cases** — rejection sampling can theoretically loop;
   cap at 1 000 attempts with a forced fallback.

---

## 17. Open Questions

1. **Optimal target scores per level** — needs playtesting to calibrate exact values.
   Blueprint uses [Estimated] formulaic progression; tune after 20-player playtest.
2. **Obstacle visual design** — ice/locked/stone tile visuals described but not pixel-designed.
   Need 1 hour of art work per obstacle type.
3. **Special + special combo effects** — Line×Line, Line×Nova, Burst×Nova behavior
   described generically; exact clear patterns need one design session to spec precisely.
4. **Background music** — whether a generative ambient track adds enough feel to justify
   the ~50 extra lines of WebAudio code; test with and without in user playtest.
5. **7th gem color** — Citrine listed for hard levels; test whether 7 colors on 8×8
   makes matches too rare (should target ~3–5 possible swaps at any board state).

---

*Blueprint version 1.0 — GemBlast — June 2026*
*Built to the 17-section game-blueprint template. All [Estimated] values are tunable.*
