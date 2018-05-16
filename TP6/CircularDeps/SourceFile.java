package CircularDeps;

import java.util.HashSet;

public class SourceFile {
    private String name;
    private HashSet<SourceFile> dependencies = new HashSet<>();

    public SourceFile(String name)
    {
        this.name = name;
    }

    public HashSet<SourceFile> getDependencies()
    {
        return this.dependencies;
    }

    public void addDependency(SourceFile file)
    {
        this.dependencies.add(file);
    }
}
