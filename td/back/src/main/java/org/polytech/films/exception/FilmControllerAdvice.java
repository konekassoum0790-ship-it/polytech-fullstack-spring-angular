package org.polytech.films.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FilmControllerAdvice {

    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleFilmNotFound(FilmNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problem.setTitle("Film non trouvé");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }

    @ExceptionHandler(CommentaireNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCommentaireNotFound(CommentaireNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problem.setTitle("Commentaire non trouvé");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }
}
