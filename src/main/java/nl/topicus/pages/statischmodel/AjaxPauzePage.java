package nl.topicus.pages.statischmodel;

import nl.topicus.pages.BasePage;
import nl.topicus.pages.getobject.DynamischModelPage;

public class AjaxPauzePage extends BasePage
{
	private static final long serialVersionUID = 1L;

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return DynamischModelPage.class;
	}
}
