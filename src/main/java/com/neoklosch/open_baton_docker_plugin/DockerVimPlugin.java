package main.java.com.neoklosch.open_baton_docker_plugin;

/*
 * Copyright (c) 2017 Neoklosch (Markus Paeschke)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class DockerVimPlugin extends VimDriver {
    private static final Logger LOG_TAG = LoggerFactory.getLogger(DockerVimPlugin.class);

    public DockerVimPlugin() {
        super();
    }

    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, TimeoutException, IllegalAccessException, InvocationTargetException  {
        if (args.length <= 1) {
            PluginStarter.registerPlugin(TestClient.class, "test", "localhost", 5672, 3);
        } else {
            PluginStarter.registerPlugin(TestClient.class, args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        }
    }
}
