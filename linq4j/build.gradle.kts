/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    id("com.github.johnrengelman.shadow")
    id("java")
}

dependencies {
    api("org.apiguardian:apiguardian-api")
    api("org.checkerframework:checker-qual")

    implementation("com.google.guava:guava")
    implementation("org.apache.calcite.avatica:avatica-core")
}

tasks.shadowJar {
    archiveClassifier.set("")
    dependencies {
        include(dependency("com.google.guava:guava:31.1-jre"))
        include(dependency("com.google.guava:failureaccess"))
    }
    relocate("com.google", "org.apache.calcite.shaded.com.google")
}

signing {
    setRequired(false)
}

tasks.jar {
    archiveClassifier.set("default")
    dependsOn(tasks.shadowJar)
}
