CREATE TABLE interviews_languages (
      interview_id UUID NOT NULL,
      language_id UUID NOT NULL,
      PRIMARY KEY (interview_id, language_id),
      FOREIGN KEY (interview_id) REFERENCES interviews(id) ON DELETE CASCADE,
      FOREIGN KEY (language_id) REFERENCES languages(id) ON DELETE CASCADE
);