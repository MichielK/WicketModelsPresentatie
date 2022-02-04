package nl.topicus;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class BasePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		add(new Label("title", getPageClass().getSimpleName()));
		addNextLink();
	}

	private void addNextLink()
	{
		Class< ? extends BasePage> nextPage = getNextPageClass();

		if (nextPage == null)
		{
			add(new WebMarkupContainer("nextLink").setVisible(false));
			return;
		}

		add(new BookmarkablePageLink<>("nextLink", nextPage));
	}

	protected abstract Class< ? extends BasePage> getNextPageClass();
}
