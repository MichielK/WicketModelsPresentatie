package nl.topicus.pages.readwrite;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import nl.topicus.pages.BasePage;

/**
 * Zoals op de vorige pagina (ReadOnlyModelPage) aangegeven kan je - dus - met `new Model<>();` 
 * een read/write-model aanmaken. Maar let er ook op dat je niet onnodig veel models gaat gebruiken.
 * Je ziet in de onSubmit dat je dan eerst data onnodig moet gaan kopieren van je inputfield naar je model  
 */
public class TeveelReadWriteModelsPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private IModel<Leerling> leerlingModel = Model.of(new Leerling());

	private IModel<String> voornaamInput = new Model<>();

	private IModel<String> achernaamInput = new Model<>();

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		Form<Void> form = new Form<>("formId");
		add(form);

		form.add(new TextField<>("voornaamInputField", voornaamInput));
		form.add(new TextField<>("achternaamInputField", achernaamInput));
		
		// Beter is om een LambdaModel te gebruiken:
		// form.add(new TextField<>("achternaamInputField", LambdaModel.of(leerlingModel, Leerling::getAchternaam, Leerling::setAchternaam)));

		form.add(new SubmitLink("submitlink", form)
		{
			private static final long serialVersionUID = 1L;

			public void onSubmit()
			{
				Leerling leerling = leerlingModel.getObject();
				leerling.setVoornaam(voornaamInput.getObject());
				leerling.setAchternaam(achernaamInput.getObject());

				// [...] doe dingen zoals opslaan

				System.out.println("Leerling " + leerling.getVoornaam() + " opgeslagen");
			};
		});
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return null;
	}
}
