def round_scores(student_scores):
    """Round all the student scores to the nearest integer."""
    return [round(score) for score in student_scores]


def count_failed_students(student_scores):
    """Return the count of students who failed (scored 40 or less)."""
    count = 0
    for score in student_scores:
        if score <= 40:
            count += 1
    return count


def above_threshold(student_scores, threshold):
    """Return a list of scores that are at or above the given threshold."""
    return [score for score in student_scores if score >= threshold]


def letter_grades(highest):
    """Return the lower threshold scores for each letter grade."""
    # Grade ranges: F <= 40, D, C, B, A (up to highest)
    step = (highest - 40) // 4
    return [41, 41 + step, 41 + step * 2, 41 + step * 3]


def student_ranking(student_scores, student_names):
    """Return a list of strings with student ranks in descending order."""
    ranking = []
    for index, (name, score) in enumerate(zip(student_names, student_scores), start=1):
        ranking.append(f"{index}. {name}: {score}")
    return ranking


def perfect_score(student_info):
    """Return the first student with a perfect score (100)."""
    for info in student_info:
        if info[1] == 100:
            return info
    return []
