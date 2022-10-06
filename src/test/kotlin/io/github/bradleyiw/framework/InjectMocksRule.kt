package io.github.bradleyiw.framework

import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class InjectMocksRule {
    companion object {
        fun create(testClass: Any) = TestRule { statement, _ ->
            MockitoAnnotations.openMocks(testClass)
            statement
        }
    }
}