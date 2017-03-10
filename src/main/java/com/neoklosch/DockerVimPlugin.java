package com.neoklosch;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Image;
import org.openbaton.catalogue.mano.common.DeploymentFlavour;
import org.openbaton.catalogue.nfvo.*;
import org.openbaton.catalogue.security.Key;
import org.openbaton.exceptions.VimDriverException;
import org.openbaton.plugin.PluginStarter;
import org.openbaton.vim.drivers.interfaces.VimDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeoutException;

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
  private static final Logger logger = LoggerFactory.getLogger(DockerVimPlugin.class);

  private DockerClient dockerClient;

  protected void createDockerInstance() {
    try {
      dockerClient = DefaultDockerClient.fromEnv().build();
    } catch (DockerCertificateException dce) {
      logger.debug(dce.getMessage());
    }
  }

  public static void main(String[] args)
      throws NoSuchMethodException, IOException, InstantiationException, TimeoutException,
          IllegalAccessException, InvocationTargetException {
    if (args.length <= 1) {
      PluginStarter.registerPlugin(DockerVimPlugin.class, "docker", "localhost", 5672, 10);
    } else {
      PluginStarter.registerPlugin(
          DockerVimPlugin.class,
          args[0],
          args[1],
          Integer.parseInt(args[2]),
          Integer.parseInt(args[3]));
    }
  }

  public Server launchInstance(
      VimInstance vimInstance,
      String name,
      String image,
      String flavor,
      String keypair,
      Set<String> network,
      Set<String> secGroup,
      String userData)
      throws VimDriverException {

    createDockerInstance();

    return null;
  }

  public List<NFVImage> listImages(VimInstance vimInstance) throws VimDriverException {
    createDockerInstance();
    List<NFVImage> images = new ArrayList<NFVImage>();
    List<Image> dockerImages = null;
    try {
      dockerImages = dockerClient.listImages();
    } catch (DockerException de) {
      logger.debug(de.getMessage());
    } catch (InterruptedException ie) {
      logger.debug(ie.getMessage());
    }
    if (dockerImages != null) {
      for (Image image : dockerImages) {
        NFVImage nfvImage = new NFVImage();
        nfvImage.setName(image.id());
        nfvImage.setContainerFormat("docker");
        nfvImage.setCreated(new Date());
        nfvImage.setIsPublic(true);
        images.add(nfvImage);
      }
    }

    return images;
  }

  public List<Server> listServer(VimInstance vimInstance) throws VimDriverException {
    return null;
  }

  public List<Network> listNetworks(VimInstance vimInstance) throws VimDriverException {
    return null;
  }

  public List<DeploymentFlavour> listFlavors(VimInstance vimInstance) throws VimDriverException {
    return null;
  }

  public Server launchInstanceAndWait(
      VimInstance vimInstance,
      String hostname,
      String image,
      String extId,
      String keyPair,
      Set<String> networks,
      Set<String> securityGroups,
      String s,
      Map<String, String> floatingIps,
      Set<Key> keys)
      throws VimDriverException {
    return null;
  }

  public Server launchInstanceAndWait(
      VimInstance vimInstance,
      String hostname,
      String image,
      String extId,
      String keyPair,
      Set<String> networks,
      Set<String> securityGroups,
      String s)
      throws VimDriverException {
    return null;
  }

  public void deleteServerByIdAndWait(VimInstance vimInstance, String id)
      throws VimDriverException {}

  public Network createNetwork(VimInstance vimInstance, Network network) throws VimDriverException {
    return null;
  }

  public DeploymentFlavour addFlavor(VimInstance vimInstance, DeploymentFlavour deploymentFlavour)
      throws VimDriverException {
    return null;
  }

  public NFVImage addImage(VimInstance vimInstance, NFVImage image, byte[] imageFile)
      throws VimDriverException {
    return null;
  }

  public NFVImage addImage(VimInstance vimInstance, NFVImage image, String image_url)
      throws VimDriverException {
    return null;
  }

  public NFVImage updateImage(VimInstance vimInstance, NFVImage image) throws VimDriverException {
    return null;
  }

  public NFVImage copyImage(VimInstance vimInstance, NFVImage image, byte[] imageFile)
      throws VimDriverException {
    return null;
  }

  public boolean deleteImage(VimInstance vimInstance, NFVImage image) throws VimDriverException {
    return false;
  }

  public DeploymentFlavour updateFlavor(
      VimInstance vimInstance, DeploymentFlavour deploymentFlavour) throws VimDriverException {
    return null;
  }

  public boolean deleteFlavor(VimInstance vimInstance, String extId) throws VimDriverException {
    return false;
  }

  public Subnet createSubnet(VimInstance vimInstance, Network createdNetwork, Subnet subnet)
      throws VimDriverException {
    return null;
  }

  public Network updateNetwork(VimInstance vimInstance, Network network) throws VimDriverException {
    return null;
  }

  public Subnet updateSubnet(VimInstance vimInstance, Network updatedNetwork, Subnet subnet)
      throws VimDriverException {
    return null;
  }

  public List<String> getSubnetsExtIds(VimInstance vimInstance, String network_extId)
      throws VimDriverException {
    return null;
  }

  public boolean deleteSubnet(VimInstance vimInstance, String existingSubnetExtId)
      throws VimDriverException {
    return false;
  }

  public boolean deleteNetwork(VimInstance vimInstance, String extId) throws VimDriverException {
    return false;
  }

  public Network getNetworkById(VimInstance vimInstance, String id) throws VimDriverException {
    return null;
  }

  public Quota getQuota(VimInstance vimInstance) throws VimDriverException {
    return null;
  }

  public String getType(VimInstance vimInstance) throws VimDriverException {
    return null;
  }
}
