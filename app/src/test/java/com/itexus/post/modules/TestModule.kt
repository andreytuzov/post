package com.itexus.post.modules

import com.itexus.post.module.apiModule
import com.itexus.post.module.interceptorModule
import com.itexus.post.module.repositoryModule

internal val testModules = interceptorModule + apiModule + repositoryModule +
        testDatabaseModule + testNetworkModule