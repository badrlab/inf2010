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
            dependentFiles = file.getDependencies();
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
        boolean result = dependentFiles.contains(file);
        if (result)
        {
            System.out.println("Direct dependency found !"); //We don't need DFS because there is a direct circular dependency
            return true;
        }

        for (SourceFile dependency : dependentFiles)
        {
            result = dfs(dependency, file);
        }
        return result;
    }

    private boolean hasMoreDependencies(SourceFile file_) //check if a file as dependencies or not. Methode used in dfs method, see below.
    {
        if (file_.getDependencies().size() !=0)
            return true;
        else 
            return false;
    }
    
    private boolean dfs(SourceFile file, SourceFile initialFile)
    {
        boolean containsInitialFile = false;
        if (hasMoreDependencies(file))
        {
            containsInitialFile = file.getDependencies().contains(initialFile); //Checking if we found the initial node of our DFS in file's dependencies
            if (containsInitialFile)
            {
                System.out.println("Indirect circular dependency found !");
                return containsInitialFile;
            }
            else
            {
                for (SourceFile dependency : file.getDependencies()) //if not then we use recursively the dfs method() until there is no more file to examine
                {
                    dfs(dependency,initialFile);
                }
            }
            
        }
        return containsInitialFile;
    }
}
