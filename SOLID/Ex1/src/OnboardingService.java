import java.util.*;

public class OnboardingService {
    private final StudentInputParser parser;
    private final StudentValidator validator;
    private final StudentRepository repo;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentInputParser parser, StudentValidator validator,
            StudentRepository repo, OnboardingPrinter printer) {
        this.parser = parser;
        this.validator = validator;
        this.repo = repo;
        this.printer = printer;
    }

    // now this just orchastrates the flow
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        StudentRawData data = parser.parse(raw);
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, data.name, data.email, data.phone, data.program);
        repo.save(rec);

        printer.printConfirmation(rec, repo.count());
    }
}
