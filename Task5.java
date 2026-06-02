import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentValidator {

    // FIX: Use SLF4J logger instead of printStackTrace()
    private static final Logger logger =
            LoggerFactory.getLogger(DocumentValidator.class);

    public ValidationResult validate(Document doc) {
        try {
            if (doc == null) {
                // FIX: Use specific exception for expected validation failure
                throw new IllegalArgumentException("Document is null");
            }

            String content = doc.extractContent();

            if (content == null || content.isEmpty()) {
                // FIX: Use specific exception for expected validation failure
                throw new IllegalArgumentException("Empty content");
            }

            return runValidationRules(content);

        } catch (IllegalArgumentException e) {

            // FIX: Expected validation failures logged as warning
            logger.warn("Validation failed: {}", e.getMessage());

            // FIX: Do not flood logs with stack traces for expected failures
            return null;

        } catch (Exception e) {

            // FIX: Unexpected runtime errors logged with stack trace
            logger.error("Unexpected validation error", e);

            return null;
        }
    }

    public void validateBatch(List<Document> docs) {

        for (Document doc : docs) {
            try {

                ValidationResult r = validate(doc);

                // FIX: Prevent NullPointerException when validate() returns null
                if (r != null && r.isValid()) {
                    saveResult(r);
                }

            } catch (Exception e) {

                // FIX: Do not silently swallow exceptions
                logger.error("Error validating document in batch", e);
            }
        }
    }
}
