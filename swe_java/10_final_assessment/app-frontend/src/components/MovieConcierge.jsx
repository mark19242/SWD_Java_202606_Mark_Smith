import { useEffect, useLayoutEffect, useRef } from "react"
import { gsap } from "gsap"

export function MovieConcierge({ activeField = "idle", usernameLength = 0 }) {
  const containerRef = useRef(null)

  const headRef = useRef(null)

  const pupilLRef = useRef(null)
  const pupilRRef = useRef(null)

  const mouthSmileRef = useRef(null)
  const mouthOpenRef = useRef(null)

  const armLRef = useRef(null)
  const armRRef = useRef(null)

  /*
   * Start the arms below the circular frame.
   * They only come up when the password field is active.
   */
  useLayoutEffect(() => {
    gsap.set([armLRef.current, armRRef.current], {
      y: 160,
    })

    gsap.set(mouthOpenRef.current, {
      opacity: 0,
      scale: 0.85,
      transformOrigin: "center center",
    })
  }, [])

  /*
   * React tells us which input is active.
   * GSAP handles the actual SVG movement.
   */
  useEffect(() => {
    const pupils = [pupilLRef.current, pupilRRef.current]

    const arms = [armLRef.current, armRRef.current]

    /*
     * Prevent old animations from fighting
     * newer input changes.
     */
    gsap.killTweensOf([
      ...pupils,
      ...arms,
      headRef.current,
      mouthSmileRef.current,
      mouthOpenRef.current,
    ])

    /*
     * PASSWORD
     * Reset the face and cover the concierge's eyes.
     */
    if (activeField === "password") {
      gsap.to(pupils, {
        x: 0,
        y: 0,
        duration: 0.2,
        ease: "power2.out",
        overwrite: true,
      })

      gsap.to(headRef.current, {
        x: 0,
        y: 0,
        rotation: 0,
        duration: 0.3,
        ease: "power2.out",
        transformOrigin: "center center",
        overwrite: true,
      })

      gsap.to(mouthSmileRef.current, {
        opacity: 1,
        duration: 0.2,
        overwrite: true,
      })

      gsap.to(mouthOpenRef.current, {
        opacity: 0,
        scale: 0.85,
        y: 2,
        duration: 0.2,
        transformOrigin: "center center",
        overwrite: true,
      })

      gsap.to(armLRef.current, {
        x: 3,
        y: 0,
        rotation: -3,
        duration: 0.38,
        ease: "power2.out",
        transformOrigin: "bottom center",
        overwrite: true,
      })

      gsap.to(armRRef.current, {
        x: -3,
        y: 0,
        rotation: 3,
        duration: 0.38,
        ease: "power2.out",
        transformOrigin: "bottom center",
        overwrite: true,
      })

      return
    }

    /*
     * If we're not in the password field,
     * send both arms back below the frame.
     */
    gsap.to(arms, {
      x: 0,
      y: 160,
      rotation: 0,
      duration: 0.42,
      ease: "power2.out",
      overwrite: true,
    })

    /*
     * USERNAME
     * Look down toward the input,
     * subtly follow the typing,
     * tilt the head,
     * and open the mouth.
     */
    if (activeField === "username") {
      const lookX = Math.min(usernameLength * 0.14, 1.8)

      const lookY = Math.min(0.8 + usernameLength * 0.03, 1.6)

      const headTurn = Math.min(usernameLength * 0.05, 1.2)

      gsap.to(pupils, {
        x: lookX,
        y: lookY,
        duration: 0.3,
        ease: "power2.out",
        overwrite: true,
      })

      gsap.to(headRef.current, {
        x: 0.5,
        y: 1.3,
        rotation: headTurn,
        duration: 0.35,
        ease: "power2.out",
        transformOrigin: "center center",
        overwrite: true,
      })

      gsap.to(mouthSmileRef.current, {
        opacity: 0,
        duration: 0.16,
        overwrite: true,
      })

      gsap.to(mouthOpenRef.current, {
        opacity: 1,
        scale: 1,
        y: 0,
        duration: 0.24,
        ease: "power2.out",
        transformOrigin: "center center",
        overwrite: true,
      })

      return
    }

    /*
     * IDLE
     * Reset everything to the neutral position.
     */
    gsap.to(pupils, {
      x: 0,
      y: 0,
      duration: 0.3,
      ease: "power2.out",
      overwrite: true,
    })

    gsap.to(headRef.current, {
      x: 0,
      y: 0,
      rotation: 0,
      duration: 0.35,
      ease: "power2.out",
      transformOrigin: "center center",
      overwrite: true,
    })

    gsap.to(mouthSmileRef.current, {
      opacity: 1,
      duration: 0.2,
      overwrite: true,
    })

    gsap.to(mouthOpenRef.current, {
      opacity: 0,
      scale: 0.85,
      y: 2,
      duration: 0.2,
      transformOrigin: "center center",
      overwrite: true,
    })
  }, [activeField, usernameLength])

  return (
    <div className="concierge-container" ref={containerRef}>
      <svg
        className="concierge-svg"
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 200 200"
        role="img"
        aria-label="ReelVibe movie theater concierge"
      >
        <defs>
          <circle id="armMaskPath" cx="100" cy="100" r="100" />

          <clipPath id="armMask">
            <use href="#armMaskPath" />
          </clipPath>

          <linearGradient id="theaterGlow" x1="0" y1="0" x2="0" y2="1">
            <stop offset="0%" stopColor="#46334f" />

            <stop offset="100%" stopColor="#211525" />
          </linearGradient>

          <linearGradient id="jacketGradient" x1="0" y1="0" x2="1" y2="1">
            <stop offset="0%" stopColor="#46334f" />

            <stop offset="100%" stopColor="#211525" />
          </linearGradient>
        </defs>

        {/* --------------------------------
            THEATER BACKGROUND
        -------------------------------- */}

        <circle cx="100" cy="100" r="100" fill="url(#theaterGlow)" />

        {/* Movie screen */}
        <rect
          x="54"
          y="22"
          width="92"
          height="52"
          rx="5"
          fill="#302139"
          stroke="#f2c230"
          strokeWidth="1.5"
          opacity="0.85"
        />

        {/* Curtains */}
        <path
          d="
            M0 0
            H42
            C36 32 38 63 48 94
            C38 87 27 84 0 86
            Z
          "
          fill="#592c45"
        />

        <path
          d="
            M200 0
            H158
            C164 32 162 63 152 94
            C162 87 173 84 200 86
            Z
          "
          fill="#592c45"
        />

        {/* Marquee arch */}
        <path
          d="
            M28 70
            C32 20 68 7 100 7
            C132 7 168 20 172 70
          "
          fill="none"
          stroke="#f2c230"
          strokeWidth="3"
        />

        {/* Marquee lights */}
        {[
          [40, 44],
          [55, 26],
          [76, 15],
          [100, 11],
          [124, 15],
          [145, 26],
          [160, 44],
        ].map(([cx, cy], index) => (
          <circle key={index} cx={cx} cy={cy} r="2.3" fill="#f2921d" />
        ))}

        {/* --------------------------------
            CONCIERGE HEAD
        -------------------------------- */}

        <g ref={headRef}>
          {/* Ears */}
          <ellipse cx="67" cy="82" rx="7" ry="10" fill="#d8a077" />

          <ellipse cx="133" cy="82" rx="7" ry="10" fill="#d8a077" />

          {/* Neck */}
          <path
            d="
              M88 112
              L88 130
              C93 136 107 136 112 130
              L112 112
              Z
            "
            fill="#d8a077"
          />

          {/* Face */}
          <path
            className="face"
            d="
              M69 58
              C69 38 82 28 100 28
              C118 28 131 38 131 58
              L131 83
              C131 106 117 122 100 122
              C83 122 69 106 69 83
              Z
            "
            fill="#d8a077"
          />

          {/* Hair */}
          <path
            className="hair"
            d="
              M67 62
              C64 43 73 25 91 21
              C105 14 122 20 130 32
              C137 41 134 55 130 62
              C125 51 118 47 112 45
              C103 50 91 51 80 47
              C75 50 71 55 67 62
              Z
            "
            fill="#171119"
          />

          {/* Hair highlight */}
          <path
            d="
              M77 38
              C88 25 108 23 122 33
            "
            fill="none"
            stroke="#46334f"
            strokeWidth="4"
            strokeLinecap="round"
            opacity="0.65"
          />

          {/* Eyebrows */}
          <path
            d="
              M79 69
              C84 66 89 66 93 69
            "
            fill="none"
            stroke="#211525"
            strokeWidth="2.5"
            strokeLinecap="round"
          />

          <path
            d="
              M107 69
              C111 66 116 66 121 69
            "
            fill="none"
            stroke="#211525"
            strokeWidth="2.5"
            strokeLinecap="round"
          />

          {/* Left eye */}
          <g className="eyeL">
            <ellipse cx="86" cy="77" rx="4.4" ry="4.8" fill="#ffffff" />

            <g ref={pupilLRef}>
              <circle cx="86" cy="77" r="2.2" fill="#211525" />

              <circle cx="85.2" cy="76.2" r="0.7" fill="#ffffff" />
            </g>
          </g>

          {/* Right eye */}
          <g className="eyeR">
            <ellipse cx="114" cy="77" rx="4.4" ry="4.8" fill="#ffffff" />

            <g ref={pupilRRef}>
              <circle cx="114" cy="77" r="2.2" fill="#211525" />

              <circle cx="113.2" cy="76.2" r="0.7" fill="#ffffff" />
            </g>
          </g>

          {/* Nose */}
          <path
            className="nose"
            d="
              M100 79
              C97 85 97 89 100 90
              C102 90 104 89 105 88
            "
            fill="none"
            stroke="#8b5c49"
            strokeWidth="1.8"
            strokeLinecap="round"
          />

          {/* Neutral smile */}
          <path
            ref={mouthSmileRef}
            d="
              M88 99
              C94 106 106 106 112 99
            "
            fill="none"
            stroke="#211525"
            strokeWidth="2.4"
            strokeLinecap="round"
          />

          {/* Open / reacting mouth */}
          <g ref={mouthOpenRef} opacity="0">
            <ellipse cx="100" cy="100" rx="10" ry="7" fill="#7c3f47" />

            {/* Teeth */}
            <rect
              x="94"
              y="94.5"
              width="12"
              height="3"
              rx="1.5"
              fill="#ffffff"
            />

            {/* Tongue */}
            <ellipse cx="100" cy="103" rx="5.5" ry="2.8" fill="#d97988" />
          </g>
        </g>

        {/* --------------------------------
            THEATER ATTENDANT UNIFORM
        -------------------------------- */}

        {/* Shirt */}
        <path
          d="
            M77 126
            L91 120
            L100 135
            L109 120
            L123 126
            L129 180
            L71 180
            Z
          "
          fill="#ffffff"
        />

        {/* Left jacket */}
        <path
          d="
            M19 178
            C29 145 51 130 78 124
            L91 159
            L100 180
            L19 200
            Z
          "
          fill="url(#jacketGradient)"
        />

        {/* Right jacket */}
        <path
          d="
            M181 178
            C171 145 149 130 122 124
            L109 159
            L100 180
            L181 200
            Z
          "
          fill="url(#jacketGradient)"
        />

        {/* Lapels */}
        <path
          d="
            M78 124
            L91 159
            L100 143
            L91 120
            Z
          "
          fill="#35273d"
        />

        <path
          d="
            M122 124
            L109 159
            L100 143
            L109 120
            Z
          "
          fill="#35273d"
        />

        {/* Gold bow tie */}
        <g className="bow-tie">
          <path
            d="
              M99 137
              L85 130
              L85 144
              Z
            "
            fill="#f2c230"
          />

          <path
            d="
              M101 137
              L115 130
              L115 144
              Z
            "
            fill="#f2c230"
          />

          <circle cx="100" cy="137" r="4.5" fill="#f2921d" />
        </g>

        {/* ReelVibe badge */}
        <g className="name-badge">
          <rect x="119" y="151" width="34" height="13" rx="3" fill="#f2c230" />

          <text
            x="136"
            y="159.5"
            textAnchor="middle"
            fontSize="5"
            fontWeight="800"
            fill="#211525"
          >
            REELVIBE
          </text>
        </g>

        {/* --------------------------------
            ARMS / HANDS
        -------------------------------- */}

        <g clipPath="url(#armMask)">
          {/* Left arm */}
          <g className="armL" ref={armLRef}>
            {/* Jacket sleeve */}
            <path
              d="
                M52 181
                C59 148 69 110 84 86
              "
              fill="none"
              stroke="#2e1f36"
              strokeWidth="16"
              strokeLinecap="round"
            />

            {/* White cuff */}
            <path
              d="
                M77 99
                L84 86
              "
              fill="none"
              stroke="#ffffff"
              strokeWidth="9"
              strokeLinecap="round"
            />

            {/* Palm */}
            <path
              d="
    M79 71
    C82 66 89 65 93 69
    C96 72 96 77 94 82
    C92 87 88 91 83 91
    C78 91 74 88 73 83
    C72 79 74 75 79 71
    Z
  "
              fill="#d8a077"
              stroke="#8b5c49"
              strokeWidth="1.6"
              strokeLinejoin="round"
            />

            {/* Finger details */}
            <path
              d="M81 72 L90 69"
              fill="none"
              stroke="#b97e63"
              strokeWidth="1.2"
              strokeLinecap="round"
            />

            <path
              d="M82 76 L92 74"
              fill="none"
              stroke="#b97e63"
              strokeWidth="1.2"
              strokeLinecap="round"
            />

            <path
              d="M82 81 L91 80"
              fill="none"
              stroke="#b97e63"
              strokeWidth="1.2"
              strokeLinecap="round"
            />
          </g>

          {/* Right arm */}
          <g className="armR" ref={armRRef}>
            {/* Jacket sleeve */}
            <path
              d="
                M148 181
                C141 148 131 110 116 86
              "
              fill="none"
              stroke="#2e1f36"
              strokeWidth="16"
              strokeLinecap="round"
            />

            {/* White cuff */}
            <path
              d="
                M123 99
                L116 86
              "
              fill="none"
              stroke="#ffffff"
              strokeWidth="9"
              strokeLinecap="round"
            />

            {/* Palm */}
            <path
              d="
    M121 71
    C118 66 111 65 107 69
    C104 72 104 77 106 82
    C108 87 112 91 117 91
    C122 91 126 88 127 83
    C128 79 126 75 121 71
    Z
  "
              fill="#d8a077"
              stroke="#8b5c49"
              strokeWidth="1.6"
              strokeLinejoin="round"
            />

            {/* Finger details */}
            <path
              d="M119 72 L110 69"
              fill="none"
              stroke="#b97e63"
              strokeWidth="1.2"
              strokeLinecap="round"
            />

            <path
              d="M118 76 L108 74"
              fill="none"
              stroke="#b97e63"
              strokeWidth="1.2"
              strokeLinecap="round"
            />

            <path
              d="M118 81 L109 80"
              fill="none"
              stroke="#b97e63"
              strokeWidth="1.2"
              strokeLinecap="round"
            />
          </g>
        </g>
      </svg>
    </div>
  )
}
