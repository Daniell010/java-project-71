package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

public class App implements Callable<String> {
    @CommandLine.Option(
            names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]",
            defaultValue = "stylish"
    )
    private String format;
    @CommandLine.Parameters(paramLabel = "filepath1", description = "path to first file.")
    private String filepath1;
    @CommandLine.Parameters(paramLabel = "filepath2", description = "path to second file.")
    private String filepath2;
    @Override
    public String call() {
        try {
            var result = Differ.generate(filepath1, filepath2, format);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
