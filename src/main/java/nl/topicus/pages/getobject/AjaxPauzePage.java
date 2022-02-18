package nl.topicus.pages.getobject;

import nl.topicus.pages.BasePage;

public class AjaxPauzePage extends BasePage
{
	private static final long serialVersionUID = 1L;

	@Override
	protected Class< ? extends BasePage> getNextPageClass()
	{
		return DynamischModelPage.class;
	}
}
