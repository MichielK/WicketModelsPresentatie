package nl.topicus.pages.readonly;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import nl.topicus.pages.BasePage;

public class ReadOnlyModelPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	private IModel<String> inputModel;

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		Form<Void> form = new Form<>("formId");
		add(form);

		inputModel = () -> "Default waarde";

		// Juiste model / juiste oplossing:
		// inputModel = new Model<>("Default waarde");

		form.add(new TextField<>("inputFieldId", inputModel));

		form.add(new SubmitLink("submitlink", form)
		{
			private static final long serialVersionUID = 1L;

			public void onSubmit()
			{
				System.out.println("Submitted: " + inputModel.getObject());
			};
		});
	}

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return null;
	}
}
