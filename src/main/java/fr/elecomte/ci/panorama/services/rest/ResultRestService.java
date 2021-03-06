package fr.elecomte.ci.panorama.services.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.elecomte.ci.panorama.data.model.ResultType;
import fr.elecomte.ci.panorama.services.model.BuildResultRecord;
import fr.elecomte.ci.panorama.services.model.DeployResultRecord;
import fr.elecomte.ci.panorama.services.model.ProjectResultListView;
import fr.elecomte.ci.panorama.services.model.ProjectView;
import fr.elecomte.ci.panorama.services.model.ReleaseResultRecord;
import fr.elecomte.ci.panorama.services.model.TestResultRecord;
import fr.elecomte.ci.panorama.services.processes.ResultInformationProcess;

/**
 * @author elecomte
 * @since 0.1.0
 */
@RestController
@RequestMapping(Constants.API_ROOT + "/results")
public class ResultRestService {

	@Autowired
	private ResultInformationProcess results;

	/**
	 * @param projectCodeName
	 * @param projectVersion
	 * @return
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/all", method = GET)
	@ResponseBody
	public ProjectResultListView projectResults(@PathVariable String projectCodeName, @PathVariable String projectVersion) {

		return this.results.getProjectResults(projectCodeName, projectVersion, null);
	}

	/**
	 * @param projectCodeName
	 * @param projectVersion
	 * @return
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/deploy", method = GET)
	@ResponseBody
	public ProjectResultListView projectDeploys(@PathVariable String projectCodeName, @PathVariable String projectVersion) {

		return this.results.getProjectResults(projectCodeName, projectVersion, ResultType.DEPLOY);
	}

	/**
	 * @param record
	 */
	@RequestMapping(value = "/deploy", method = PUT)
	@ResponseBody
	public void recordDeploy(@RequestBody @Valid DeployResultRecord record) {

		this.results.recordResultInformation(record);
	}

	/**
	 * @param record
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/deploy", method = PUT)
	@ResponseBody
	public void recordDeploy(@PathVariable String projectCodeName, @PathVariable String projectVersion,
			@RequestBody @Valid DeployResultRecord record) {

		record.setProject(new ProjectView(projectCodeName, projectVersion));

		this.results.recordResultInformation(record);
	}

	/**
	 * @param record
	 */
	@RequestMapping(value = "/build", method = PUT)
	@ResponseBody
	public void recordBuild(@RequestBody @Valid BuildResultRecord record) {

		this.results.recordResultInformation(record);
	}

	/**
	 * @param projectCodeName
	 * @param projectVersion
	 * @return
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/build", method = GET)
	@ResponseBody
	public ProjectResultListView projectBuilds(@PathVariable String projectCodeName, @PathVariable String projectVersion) {

		return this.results.getProjectResults(projectCodeName, projectVersion, ResultType.BUILD);
	}

	/**
	 * @param record
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/build", method = PUT)
	@ResponseBody
	public void recordBuild(@PathVariable String projectCodeName, @PathVariable String projectVersion,
			@RequestBody @Valid BuildResultRecord record) {

		record.setProject(new ProjectView(projectCodeName, projectVersion));

		this.results.recordResultInformation(record);
	}

	/**
	 * @param projectCodeName
	 * @param projectVersion
	 * @return
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/test", method = GET)
	@ResponseBody
	public ProjectResultListView projectTests(@PathVariable String projectCodeName, @PathVariable String projectVersion) {

		return this.results.getProjectResults(projectCodeName, projectVersion, ResultType.TEST);
	}

	/**
	 * @param record
	 */
	@RequestMapping(value = "/test", method = PUT)
	@ResponseBody
	public void recordTest(@RequestBody @Valid TestResultRecord record) {

		this.results.recordResultInformation(record);
	}

	/**
	 * @param record
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/test", method = PUT)
	@ResponseBody
	public void recordTest(@PathVariable String projectCodeName, @PathVariable String projectVersion,
			@RequestBody @Valid TestResultRecord record) {

		record.setProject(new ProjectView(projectCodeName, projectVersion));

		this.results.recordResultInformation(record);
	}

	/**
	 * @param projectCodeName
	 * @param projectVersion
	 * @return
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/release", method = GET)
	@ResponseBody
	public ProjectResultListView projectReleases(@PathVariable String projectCodeName, @PathVariable String projectVersion) {

		return this.results.getProjectResults(projectCodeName, projectVersion, ResultType.RELEASE);
	}

	/**
	 * @param record
	 */
	@RequestMapping(value = "/release", method = PUT)
	@ResponseBody
	public void recordRelease(@RequestBody @Valid ReleaseResultRecord record) {

		this.results.recordResultInformation(record);
	}

	/**
	 * @param record
	 */
	@RequestMapping(value = "/{projectCodeName}/{projectVersion}/release", method = PUT)
	@ResponseBody
	public void recordRelease(@PathVariable String projectCodeName, @PathVariable String projectVersion,
			@RequestBody @Valid ReleaseResultRecord record) {

		record.setProject(new ProjectView(projectCodeName, projectVersion));

		this.results.recordResultInformation(record);
	}
}
