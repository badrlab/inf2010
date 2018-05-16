package CircularDeps;

import java.util.HashSet;

public class CodeBase {
    
    private SourceFile[] sourceFiles;

    public CodeBase(SourceFile[] sourceFiles)
    {
        this.sourceFiles = sourceFiles;
    }

    public boolean hasCircularDependencies()
    {
        for (SourceFile file : this.sourceFiles) {
            HashSet<SourceFile> dependentFiles = new HashSet<>();
            //dependentFiles = file.getDependencies();
            if (existsCircularDependencies(file, dependentFiles)) {
                return true;
            }
        }
        return false;
    }

    // DFS partant de <<file>> détectant s'il existe
    // des dépendances circulaires dans les fichiers source.
    private boolean existsCircularDependencies(SourceFile file, HashSet<SourceFile> dependentFiles) //<<file>> is the initial file
    {
    	if (dependentFiles.contains(file)) return true;
    	dependentFiles.add(file);
    	for (SourceFile dep : file.getDependencies()) {
    		if (existsCircularDependencies(dep,dependentFiles)) return true;
    	}
    	return false;
    }
}