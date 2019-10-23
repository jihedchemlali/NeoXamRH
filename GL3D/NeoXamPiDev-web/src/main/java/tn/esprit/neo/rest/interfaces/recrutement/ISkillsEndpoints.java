package tn.esprit.neo.rest.interfaces.recrutement;

import javax.ws.rs.core.Response;

import tn.esprit.entities.Skills;
import tn.esprit.entities.SkillsId;

public interface ISkillsEndpoints {
	/* POST
	 * Consumes:JSON
	 */
	public Response create(SkillsId id);
	/* PUT
	 * path:{/id}
	 * Consume JSON
	 */
	public Response update(SkillsId id,Skills upSkills);//PathParam 
	/* DELETE
	 * path:{id}
	 */
	public Response delete(SkillsId delCarrire);//PathParam("id")
}
