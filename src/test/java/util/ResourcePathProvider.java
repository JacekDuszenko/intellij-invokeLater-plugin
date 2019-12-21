package util;

import static util.TestConstants.TEST_RESOURCES_RELATIVE_PATH;

import com.intellij.openapi.application.PathManager;

public class ResourcePathProvider {

  private static final String relativeResourcesPath = "/../../../resources/test";

  public static String getResourcePath(Class resourceRootClass) {
    String testInstrumentedPath = PathManager.getResourceRoot(resourceRootClass, TEST_RESOURCES_RELATIVE_PATH);
    return testInstrumentedPath + relativeResourcesPath;
  }
}
