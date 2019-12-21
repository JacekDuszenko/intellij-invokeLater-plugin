import static util.Constants.QUICKFIX_INVOKE_LATER_NAME;
import static util.Constants.QUICKFIX_INVOKE_LATER_REFERENCE_NAME;
import static util.TestConstants.EMPTY_STRING;
import static util.TestConstants.JAVA_FILE_EXTENSION;
import static util.TestConstants.METHOD_CALL_TEST_PREFIX;
import static util.TestConstants.METHOD_REFERENCE_TEST_PREFIX;
import static util.TestConstants.TEST_RESOURCE_AFTER_SUFFIX;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.testFramework.UsefulTestCase;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.CodeInsightTestFixture;
import com.intellij.testFramework.fixtures.IdeaProjectTestFixture;
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory;
import com.intellij.testFramework.fixtures.JavaTestFixtureFactory;
import com.intellij.testFramework.fixtures.TestFixtureBuilder;
import inspection.InvokeLaterInspection;
import java.util.List;
import java.util.Objects;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import util.ResourcePathProvider;

public class TestInvokeLaterInspection extends UsefulTestCase {

  private final String dataPath = ResourcePathProvider.getResourcePath(TestInvokeLaterInspection.class);
  private CodeInsightTestFixture invokeLaterTestFixture;

  @Before
  public void setUp() throws Exception {
    super.setUp();
    Objects.requireNonNull(dataPath);
    final IdeaTestFixtureFactory fixtureFactory = IdeaTestFixtureFactory.getFixtureFactory();
    final TestFixtureBuilder<IdeaProjectTestFixture> testFixtureBuilder = fixtureFactory.createFixtureBuilder(getName());
    invokeLaterTestFixture = JavaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(testFixtureBuilder.getFixture());
    invokeLaterTestFixture.setTestDataPath(dataPath);
    final JavaModuleFixtureBuilder builder = testFixtureBuilder.addModule(JavaModuleFixtureBuilder.class);

    builder.addContentRoot(invokeLaterTestFixture.getTempDirPath()).addSourceRoot(EMPTY_STRING);
    builder.setMockJdkLevel(JavaModuleFixtureBuilder.MockJdkLevel.jdk15);
    invokeLaterTestFixture.setUp();
  }

  @Override
  @After
  public void tearDown() throws Exception {
    invokeLaterTestFixture.tearDown();
    invokeLaterTestFixture = null;
  }

  private void doTest(String testName, String hint) {
    invokeLaterTestFixture.configureByFile(testName + JAVA_FILE_EXTENSION);

    invokeLaterTestFixture.enableInspections(InvokeLaterInspection.class);
    List<HighlightInfo> highlightInfos = invokeLaterTestFixture.doHighlighting();
    Assert.assertFalse(highlightInfos.isEmpty());
    final IntentionAction action = invokeLaterTestFixture.findSingleIntention(hint);

    Assert.assertNotNull(action);
    invokeLaterTestFixture.launchAction(action);
    invokeLaterTestFixture.checkResultByFile(testName + TEST_RESOURCE_AFTER_SUFFIX);
  }

  /**
   * Test the method call case
   */
  public void testInvokeLaterMethodCall() {
    doTest(METHOD_CALL_TEST_PREFIX, QUICKFIX_INVOKE_LATER_NAME);
  }

  /**
   * Test the method reference case
   */
  public void testInvokeLaterMethodReference() {
    doTest(METHOD_REFERENCE_TEST_PREFIX, QUICKFIX_INVOKE_LATER_REFERENCE_NAME);
  }
}
