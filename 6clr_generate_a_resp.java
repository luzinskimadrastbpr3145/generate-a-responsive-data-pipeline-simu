import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ResponsiveDataPipelineSimulator {

    private static final int NUM_SIMULATED_DATA_SOURCES = 5;
    private static final int NUM_SIMULATED_PIPELINES = 3;

    private List<Pipeline> pipelines;
    private List<DataSource> dataSources;

    public ResponsiveDataPipelineSimulator() {
        this.pipelines = new ArrayList<>();
        this.dataSources = new ArrayList<>();

        for (int i = 0; i < NUM_SIMULATED_PIPELINES; i++) {
            pipelines.add(new Pipeline("Pipeline-" + (i + 1)));
        }

        for (int i = 0; i < NUM_SIMULATED_DATA_SOURCES; i++) {
            dataSources.add(new DataSource("DataSource-" + (i + 1)));
        }
    }

    public void simulatePipeline() {
        Random random = new Random();

        while (true) {
            for (DataSource dataSource : dataSources) {
                int pipelineIndex = random.nextInt(NUM_SIMULATED_PIPELINES);
                Pipeline pipeline = pipelines.get(pipelineIndex);
                pipeline.handleData(dataSource.generateData());
                System.out.println("Data sent from " + dataSource.getName() + " to " + pipeline.getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ResponsiveDataPipelineSimulator simulator = new ResponsiveDataPipelineSimulator();
        simulator.simulatePipeline();
    }
}

class Pipeline {
    private String name;

    public Pipeline(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void handleData(String data) {
        System.out.println("Handling data in pipeline: " + data);
    }
}

class DataSource {
    private String name;

    public DataSource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String generateData() {
        return "Data from " + name;
    }
}