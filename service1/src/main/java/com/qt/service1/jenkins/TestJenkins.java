package com.qt.service1.jenkins;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiongyang.wjy
 * @since 2021/7/10
 */
public class TestJenkins {

    public static void main(String[] args) {
        JenkinsClient client = JenkinsClient.builder()
                .endPoint("http://192.168.0.40:8088") // Optional. Defaults to http://127.0.0.1:8080
                .credentials("chengbi:chengbi") // Optional.
                .build();


        SystemInfo systemInfo = client.api().systemApi().systemInfo();
        System.out.println(systemInfo.jenkinsVersion());

        JobList jobList = client.api().jobsApi().jobList("");
        String name = jobList.jobs().get(0).name();

        Map<String, List<String>> params = new HashMap<>(32);
        params.put("REPO_URL", Lists.newArrayList("https://e.coding.net/quadtalent/research-development-efficiency/research-development-platform.git"));
        params.put("PROJECT_NAME", Lists.newArrayList("research-development-platform"));
        params.put("GIT_CREDENTIALS_ID",Lists.newArrayList("coding-up"));
        params.put("BRANCH", Lists.newArrayList("dev"));
        params.put("BUILD_COMMAND", Lists.newArrayList("mvn -T 2C package --settings /root/.m2/conf/settings.xml  -Dmaven.test.skip=true "));
        params.put("HARBOR_CREDENTIALS_ID", Lists.newArrayList("coding-up"));
        params.put("HARBOR_ADDRESS", Lists.newArrayList("quadtalent-docker.pkg.coding.net"));
        params.put("REGISTRY_DIR", Lists.newArrayList("research-development-efficiency/docker-hub"));
        params.put("IMAGE_NAME", Lists.newArrayList("rdp"));
        params.put("DOCKERFILE_PATH", Lists.newArrayList("./deploy/docker/Dockerfile"));
        params.put("DEPLOY", Lists.newArrayList("true"));
        params.put("KUBECONFIG_PATH", Lists.newArrayList("/mnt/.kube/config"));
        params.put("DEPLOY_TYPE", Lists.newArrayList("deployment"));
        params.put("CONTAINER_NAME", Lists.newArrayList("rdp"));
        params.put("NAMESPACE", Lists.newArrayList("default"));
        params.put("CLUSTER", Lists.newArrayList("kubernetes-admin@kubernetes"));

        IntegerResponse build = client.api().jobsApi().buildWithParameters(null, name, params);
        System.out.println(build);
    }
}
