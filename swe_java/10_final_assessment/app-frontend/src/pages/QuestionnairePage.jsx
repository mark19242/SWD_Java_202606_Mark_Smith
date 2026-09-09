import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { useAuth } from "../context/AuthContext"

const questions = [
  {
    key: "currentFeeling",
    eyebrow: "Question 1 of 5",
    title: "How are you feeling right now?",
    description: "Start with the mood you're bringing into movie night.",
    multiple: false,
    options: [
      ["RELAXED", "Relaxed"],
      ["STRESSED", "Stressed"],
      ["TIRED", "Tired"],
      ["HAPPY", "Happy"],
      ["DOWN", "A little down"],
      ["BORED", "Bored"],
      ["ENERGETIC", "Energetic"],
      ["RESTLESS", "Restless"],
    ],
  },
  {
    key: "desiredFeeling",
    eyebrow: "Question 2 of 5",
    title: "How do you want the movie to make you feel?",
    description: "This matters more than your current mood.",
    multiple: false,
    options: [
      ["MAKE_ME_LAUGH", "Make me laugh"],
      ["COMFORT_ME", "Comfort me"],
      ["GET_ME_EXCITED", "Get me excited"],
      ["SCARE_ME", "Scare me"],
      ["BLOW_MY_MIND", "Blow my mind"],
      ["MAKE_ME_THINK", "Make me think"],
      ["GIVE_ME_THE_FEELS", "Give me the feels"],
      ["HELP_ME_ESCAPE", "Help me escape"],
    ],
  },
  {
    key: "movieVibes",
    eyebrow: "Question 3 of 5",
    title: "What's your movie vibe?",
    description: "Choose up to two — or let ReelVibe surprise you.",
    multiple: true,
    options: [
      ["LIGHT_AND_FUNNY", "Light & funny"],
      ["ACTION_PACKED", "Action packed"],
      ["ROMANTIC", "Romantic"],
      ["SUSPENSEFUL", "Suspenseful"],
      ["DARK_AND_INTENSE", "Dark & intense"],
      ["MIND_BENDING", "Mind bending"],
      ["EMOTIONAL", "Emotional"],
      ["EPIC_AND_ADVENTUROUS", "Epic & adventurous"],
      ["SURPRISE_ME", "Surprise me"],
    ],
  },
  {
    key: "intensity",
    eyebrow: "Question 4 of 5",
    title: "How intense should tonight's movie be?",
    description: "Set the energy level for your recommendation.",
    multiple: false,
    options: [
      ["CHILL", "Keep it chill"],
      ["LIGHT_INTENSITY", "A little intensity"],
      ["BRING_IT_ON", "Bring it on"],
      ["GO_ALL_OUT", "Go all out"],
      ["ANY_INTENSITY", "Anything goes"],
    ],
  },
  {
    key: "runtimePreference",
    eyebrow: "Question 5 of 5",
    title: "How much time do you have?",
    description: "We'll use this to filter out movies that run too long.",
    multiple: false,
    options: [
      ["QUICK", "Keep it quick"],
      ["STANDARD", "Standard movie night"],
      ["EXTENDED", "I've got time"],
      ["ANY_RUNTIME", "Runtime doesn't matter"],
    ],
  },
]

export function QuestionnairePage() {
  const { authedFetch } = useAuth()
  const navigate = useNavigate()

  const [step, setStep] = useState(0)

  const [answers, setAnswers] = useState({
    currentFeeling: "",
    desiredFeeling: "",
    movieVibes: [],
    intensity: "",
    runtimePreference: "",
  })

  const [error, setError] = useState(null)
  const [submitting, setSubmitting] = useState(false)

  const question = questions[step]

  function handleSingleAnswer(value) {
    setAnswers((current) => ({
      ...current,
      [question.key]: value,
    }))
  }

  function handleMovieVibe(value) {
    setAnswers((current) => {
      const selected = current.movieVibes

      if (value === "SURPRISE_ME") {
        return {
          ...current,
          movieVibes: ["SURPRISE_ME"],
        }
      }

      const withoutSurprise = selected.filter((item) => item !== "SURPRISE_ME")

      if (withoutSurprise.includes(value)) {
        return {
          ...current,
          movieVibes: withoutSurprise.filter((item) => item !== value),
        }
      }

      if (withoutSurprise.length >= 2) {
        return current
      }

      return {
        ...current,
        movieVibes: [...withoutSurprise, value],
      }
    })
  }

  function isSelected(value) {
    if (question.multiple) {
      return answers.movieVibes.includes(value)
    }

    return answers[question.key] === value
  }

  function hasAnswer() {
    if (question.multiple) {
      return answers.movieVibes.length > 0
    }

    return Boolean(answers[question.key])
  }

  function handleNext() {
    if (!hasAnswer()) {
      return
    }

    setStep((current) => current + 1)
  }

  function handleBack() {
    setStep((current) => Math.max(0, current - 1))
  }

  async function handleSubmit() {
    if (!hasAnswer()) {
      return
    }

    setError(null)
    setSubmitting(true)

    try {
      const response = await authedFetch("/recommendations", {
        method: "POST",
        body: answers,
      })

      navigate("/recommendations", {
        state: {
          recommendationResponse: response,
        },
      })
    } catch {
      setError("ReelVibe couldn't generate recommendations. Please try again.")
    } finally {
      setSubmitting(false)
    }
  }

  const progress = ((step + 1) / questions.length) * 100

  return (
    <main className="questionnaire-page">
      <section className="questionnaire-card">
        <div className="questionnaire-progress">
          <div
            className="questionnaire-progress-bar"
            style={{
              width: `${progress}%`,
            }}
          />
        </div>

        <p className="questionnaire-eyebrow">{question.eyebrow}</p>

        <h1 className="questionnaire-title display-font">{question.title}</h1>

        <p className="questionnaire-description">{question.description}</p>

        <div className="questionnaire-options">
          {question.options.map(([value, label]) => (
            <button
              key={value}
              type="button"
              className={[
                "vibe-option",
                value === "SURPRISE_ME" ? "surprise-option" : "",
                isSelected(value) ? "selected" : "",
              ]
                .filter(Boolean)
                .join(" ")}
              onClick={() =>
                question.multiple
                  ? handleMovieVibe(value)
                  : handleSingleAnswer(value)
              }
            >
              {label}
            </button>
          ))}
        </div>

        {error && <p className="auth-error">{error}</p>}

        <div className="questionnaire-actions">
          {step > 0 && (
            <button
              type="button"
              className="questionnaire-back"
              onClick={handleBack}
            >
              ← Back
            </button>
          )}

          {step < questions.length - 1 ? (
            <button
              type="button"
              className="questionnaire-next"
              disabled={!hasAnswer()}
              onClick={handleNext}
            >
              Next →
            </button>
          ) : (
            <button
              type="button"
              className="questionnaire-next"
              disabled={!hasAnswer() || submitting}
              onClick={handleSubmit}
            >
              {submitting ? "Finding your movies..." : "Find My ReelVibe"}
            </button>
          )}
        </div>
      </section>
    </main>
  )
}
