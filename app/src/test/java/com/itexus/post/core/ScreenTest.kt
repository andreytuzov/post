package com.itexus.post.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.module.Module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

@RunWith(JUnit4::class)
abstract class ScreenTest : KoinTest {

    abstract val modules: List<Module>

    @get:Rule
    var injectDependenciesRule = KoinTestRule.create {
        modules(modules)
    }

    @get:Rule
    var mockProvider = MockProviderRule.create {
        Mockito.mock(it.java)
    }

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
}