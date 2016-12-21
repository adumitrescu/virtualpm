package com.vm.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.Subtitle;
import com.vaadin.addon.charts.model.TickmarkPlacement;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vm.component.SparklineChart;
import com.vm.component.TopGrossingMoviesChart;
import com.vm.event.DashboardEvent.CloseOpenWindowsEvent;
import com.vm.event.DashboardEventBus;
import com.vm.model.IndicatorResponseModel;
import com.vm.model.SonarIndicatorResponse;
import com.vm.service.IssuesMongoService;
import com.vm.service.SonarIndicatorService;
import com.vm.utils.DummyDataGenerator;
import com.vm.view.DashboardEdit.DashboardEditListener;

@SuppressWarnings("serial")
public final class DashboardView extends Panel implements View, DashboardEditListener {
	
	public static final String EDIT_ID = "dashboard-edit";
	public static final String TITLE_ID = "dashboard-title";

	private Label titleLabel;
	private NotificationsButton notificationsButton;
	private CssLayout dashboardPanels;
	private final VerticalLayout root;
	// private Window notificationsWindow;
	private SonarIndicatorService sonarIndicatorService = WebApplicationContextUtils.getRequiredWebApplicationContext(
			VaadinServlet.getCurrent().getServletContext()).getBean(SonarIndicatorService.class);
	private IssuesMongoService issuesMongoService = WebApplicationContextUtils.getRequiredWebApplicationContext(
			VaadinServlet.getCurrent().getServletContext()).getBean(IssuesMongoService.class);

	public DashboardView() {
		addStyleName(ValoTheme.PANEL_BORDERLESS);
		setSizeFull();
		DashboardEventBus.register(this);

		root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(true);
		root.addStyleName("dashboard-view");
		setContent(root);
		Responsive.makeResponsive(root);

		root.addComponent(buildHeader());

		root.addComponent(buildSparklines());

		Component content = buildContent();
		root.addComponent(content);
		root.setExpandRatio(content, 1);

		// All the open sub-windows should be closed whenever the root layout
		// gets clicked.
		root.addLayoutClickListener(new LayoutClickListener() {

			@Override
			public void layoutClick(final LayoutClickEvent event) {
				DashboardEventBus.post(new CloseOpenWindowsEvent());
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Component buildSparklines() {
		CssLayout sparks = new CssLayout();
		sparks.addStyleName("sparks");
		sparks.setWidth("100%");
		Responsive.makeResponsive(sparks);

		SonarIndicatorResponse response = sonarIndicatorService.getSonarIndicators();
		List<Long> infoIssues = response.getInfoIssues();
		SparklineChart s = new SparklineChart("Info Issues", "", "", DummyDataGenerator.chartColors[0], infoIssues,
				infoIssues.toArray(), Collections.min(infoIssues), Collections.max(infoIssues));
		sparks.addComponent(s);

		List<Long> minorIssues = response.getMinorIssues();
		s = new SparklineChart("Minor Issues", "", "", DummyDataGenerator.chartColors[1], minorIssues, minorIssues
				.toArray(), Collections.min(minorIssues), Collections.max(minorIssues));
		sparks.addComponent(s);

		List<Long> majorIssues = response.getMajorIssues();
		s = new SparklineChart("Major Issues", "", "", DummyDataGenerator.chartColors[2], majorIssues, majorIssues
				.toArray(), Collections.min(majorIssues), Collections.max(majorIssues));
		sparks.addComponent(s);

		List<Long> criticalIssues = response.getCriticalIssues();
		s = new SparklineChart("Critical Issues", "", "", DummyDataGenerator.chartColors[3], criticalIssues,
				criticalIssues.toArray(), Collections.min(criticalIssues), Collections.max(criticalIssues));
		sparks.addComponent(s);

		List<Long> blockerIssues = response.getBlockerIssues();
		s = new SparklineChart("Blocker Issues", "", "", DummyDataGenerator.chartColors[4], blockerIssues, blockerIssues
				.toArray(), Collections.min(blockerIssues), Collections.max(blockerIssues));
		sparks.addComponent(s);

		List<Long> debt = response.getDebt();
		s = new SparklineChart("Debt[hours]", "", "", DummyDataGenerator.chartColors[0], debt, debt.toArray(),
				Collections.min(debt), Collections.max(debt));
		sparks.addComponent(s);

		List<Double> coverage = response.getCoverage();
		s = new SparklineChart("Coverage", "%", "", DummyDataGenerator.chartColors[2], coverage, coverage.toArray(),
				Collections.min(coverage), Collections.max(coverage));
		sparks.addComponent(s);

		List<Double> tehnicalDebtRatio = response.getTehnicalDebtRatio();
		s = new SparklineChart("Technical Debt Ratio", "%", "", DummyDataGenerator.chartColors[3], tehnicalDebtRatio,
				tehnicalDebtRatio.toArray(), Collections.min(tehnicalDebtRatio), Collections.max(tehnicalDebtRatio));
		sparks.addComponent(s);

		return sparks;
	}

	private Component buildHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.addStyleName("viewheader");
		header.setSpacing(true);

		titleLabel = new Label("Dashboard");
		titleLabel.setId(TITLE_ID);
		titleLabel.setSizeUndefined();
		titleLabel.addStyleName(ValoTheme.LABEL_H1);
		titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		header.addComponent(titleLabel);

		notificationsButton = buildNotificationsButton();
		Component edit = buildEditButton();
		HorizontalLayout tools = new HorizontalLayout(notificationsButton, edit);
		tools.setSpacing(true);
		tools.addStyleName("toolbar");
		header.addComponent(tools);

		return header;
	}

	private NotificationsButton buildNotificationsButton() {
		NotificationsButton result = new NotificationsButton();
		result.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(final ClickEvent event) {
				// openNotificationsPopup(event);
			}
		});
		return result;
	}

	private Component buildEditButton() {
		Button result = new Button();
		result.setId(EDIT_ID);
		result.setIcon(FontAwesome.EDIT);
		result.addStyleName("icon-edit");
		result.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		result.setDescription("Edit Dashboard");
		result.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(final ClickEvent event) {
				getUI().addWindow(new DashboardEdit(DashboardView.this, titleLabel.getValue()));
			}
		});
		return result;
	}

	private Component buildContent() {
		dashboardPanels = new CssLayout();
		dashboardPanels.addStyleName("dashboard-panels");
		Responsive.makeResponsive(dashboardPanels);
		dashboardPanels.addComponent(buildCummulativeFlow());
		dashboardPanels.addComponent(buildTopGrossingMovies());
		//dashboardPanels.addComponent(buildNotes());
	
		// dashboardPanels.addComponent(buildPopularMovies());

		return dashboardPanels;
	}

	private Component buildNotes() {
		TextArea notes = new TextArea("Notes");
		notes.setValue(
				"Remember to:\n· Zoom in and out in the Sales view\n· Filter the transactions and drag a set of them to the Reports tab\n· Create a new report\n· Change the schedule of the movie theater");
		notes.setSizeFull();
		notes.addStyleName(ValoTheme.TEXTAREA_BORDERLESS);
		Component panel = createContentWrapper(notes);
		panel.addStyleName("notes");
		return panel;
	}

	private Component buildCummulativeFlow() {

		Chart chart = new Chart(ChartType.AREA);

		Configuration conf = chart.getConfiguration();

		conf.setTitle(new Title("title1"));
		conf.setSubTitle(new Subtitle("Subtitile2"));

		XAxis xAxis = new XAxis();
		xAxis.setTickmarkPlacement(TickmarkPlacement.ON);
		xAxis.setCategories("29-11-2016","29-12-2016");
		conf.addxAxis(xAxis);

		YAxis yAxis = new YAxis();
		yAxis.setTitle(new AxisTitle(""));
		Labels labels = new Labels();
		labels.setFormatter("this.value / 1000");
		yAxis.setLabels(labels);
		conf.addyAxis(yAxis);

		Tooltip tooltip = new Tooltip();
		tooltip.setFormatter("this.x +': '+ Highcharts.numberFormat(this.y, 0, ',') +' millions'");
		conf.setTooltip(tooltip);

		PlotOptionsArea plotOptions = new PlotOptionsArea();
		plotOptions.setStacking(Stacking.NORMAL);
		conf.setPlotOptions(plotOptions);

		List<Series> series = new ArrayList<Series>();
		series.add(new ListSeries("Asia", 502, 635, 809, 947, 1402, 3634, 5268));
		series.add(new ListSeries("Africa", 106, 107, 111, 133, 221, 767, 1766));
		series.add(new ListSeries("Europe", 163, 203, 276, 408, 547, 729, 628));
		series.add(new ListSeries("America", 18, 31, 54, 156, 339, 818, 1201));
		series.add(new ListSeries("Ocenia", 2, 2, 2, 6, 13, 30, 46));
		conf.setSeries(series);

		chart.drawChart(conf);

		return chart;
	}

	private Component buildTopGrossingMovies() {
		List<IndicatorResponseModel> list = issuesMongoService.dashboardJiraIndicator();
		TopGrossingMoviesChart topGrossingMoviesChart = new TopGrossingMoviesChart(list);
		topGrossingMoviesChart.setSizeFull();
		return createContentWrapper(topGrossingMoviesChart);
	}

	private Component createContentWrapper(final Component content) {
		final CssLayout slot = new CssLayout();
		slot.setWidth("100%");
		slot.addStyleName("dashboard-panel-slot");

		CssLayout card = new CssLayout();
		card.setWidth("100%");
		card.addStyleName(ValoTheme.LAYOUT_CARD);

		HorizontalLayout toolbar = new HorizontalLayout();
		toolbar.addStyleName("dashboard-panel-toolbar");
		toolbar.setWidth("100%");

		Label caption = new Label(content.getCaption());
		caption.addStyleName(ValoTheme.LABEL_H4);
		caption.addStyleName(ValoTheme.LABEL_COLORED);
		caption.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		content.setCaption(null);

		MenuBar tools = new MenuBar();
		tools.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		MenuItem max = tools.addItem("", FontAwesome.EXPAND, new Command() {

			@Override
			public void menuSelected(final MenuItem selectedItem) {
				if (!slot.getStyleName().contains("max")) {
					selectedItem.setIcon(FontAwesome.COMPRESS);
					toggleMaximized(slot, true);
				} else {
					slot.removeStyleName("max");
					selectedItem.setIcon(FontAwesome.EXPAND);
					toggleMaximized(slot, false);
				}
			}
		});
		max.setStyleName("icon-only");
		// MenuItem root = tools.addItem("", FontAwesome.COG, null);
		// root.addItem("Configure", new Command() {
		// @Override
		// public void menuSelected(final MenuItem selectedItem) {
		// Notification.show("Not implemented in this demo");
		// }
		// });
		// root.addSeparator();
		// root.addItem("Close", new Command() {
		// @Override
		// public void menuSelected(final MenuItem selectedItem) {
		// Notification.show("Not implemented in this demo");
		// }
		// });

		toolbar.addComponents(caption, tools);
		toolbar.setExpandRatio(caption, 1);
		//toolbar.setComponentAlignment(caption, Alignment.);

		card.addComponents(toolbar, content);
		slot.addComponent(card);
		return slot;
	}

	/*
	 * private void openNotificationsPopup(final ClickEvent event) { VerticalLayout notificationsLayout = new
	 * VerticalLayout(); notificationsLayout.setMargin(true); notificationsLayout.setSpacing(true);
	 * 
	 * Label title = new Label("Notifications"); title.addStyleName(ValoTheme.LABEL_H3);
	 * title.addStyleName(ValoTheme.LABEL_NO_MARGIN); notificationsLayout.addComponent(title);
	 * 
	 * Collection<DashboardNotification> notifications = DashboardUI .getDataProvider().getNotifications();
	 * DashboardEventBus.post(new NotificationsCountUpdatedEvent());
	 * 
	 * for (DashboardNotification notification : notifications) { VerticalLayout notificationLayout = new
	 * VerticalLayout(); notificationLayout.addStyleName("notification-item");
	 * 
	 * Label titleLabel = new Label(notification.getFirstName() + " " + notification.getLastName() + " " +
	 * notification.getAction()); titleLabel.addStyleName("notification-title");
	 * 
	 * Label timeLabel = new Label(notification.getPrettyTime()); timeLabel.addStyleName("notification-time");
	 * 
	 * Label contentLabel = new Label(notification.getContent()); contentLabel.addStyleName("notification-content");
	 * 
	 * notificationLayout.addComponents(titleLabel, timeLabel, contentLabel);
	 * notificationsLayout.addComponent(notificationLayout); }
	 * 
	 * HorizontalLayout footer = new HorizontalLayout(); footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
	 * footer.setWidth("100%"); Button showAll = new Button("View All Notifications", new ClickListener() {
	 * 
	 * @Override public void buttonClick(final ClickEvent event) { Notification.show("Not implemented in this demo"); }
	 * }); showAll.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED); showAll.addStyleName(ValoTheme.BUTTON_SMALL);
	 * footer.addComponent(showAll); footer.setComponentAlignment(showAll, Alignment.TOP_CENTER);
	 * notificationsLayout.addComponent(footer);
	 * 
	 * if (notificationsWindow == null) { notificationsWindow = new Window(); notificationsWindow.setWidth(300.0f,
	 * Unit.PIXELS); notificationsWindow.addStyleName("notifications"); notificationsWindow.setClosable(false);
	 * notificationsWindow.setResizable(false); notificationsWindow.setDraggable(false);
	 * notificationsWindow.setCloseShortcut(KeyCode.ESCAPE, null); notificationsWindow.setContent(notificationsLayout);
	 * }
	 * 
	 * if (!notificationsWindow.isAttached()) { notificationsWindow.setPositionY(event.getClientY() -
	 * event.getRelativeY() + 40); getUI().addWindow(notificationsWindow); notificationsWindow.focus(); } else {
	 * notificationsWindow.close(); } }
	 */

	@Override
	public void enter(final ViewChangeEvent event) {
		// notificationsButton.updateNotificationsCount(null);
	}

	@Override
	public void dashboardNameEdited(final String name) {
		titleLabel.setValue(name);
	}

	private void toggleMaximized(final Component panel, final boolean maximized) {
		for (Iterator<Component> it = root.iterator(); it.hasNext();) {
			it.next().setVisible(!maximized);
		}
		dashboardPanels.setVisible(true);

		for (Iterator<Component> it = dashboardPanels.iterator(); it.hasNext();) {
			Component c = it.next();
			c.setVisible(!maximized);
		}

		if (maximized) {
			panel.setVisible(true);
			panel.addStyleName("max");
		} else {
			panel.removeStyleName("max");
		}
	}

	public static final class NotificationsButton extends Button {

		private static final String STYLE_UNREAD = "unread";
		public static final String ID = "dashboard-notifications";

		public NotificationsButton() {
			setIcon(FontAwesome.BELL);
			setId(ID);
			addStyleName("notifications");
			addStyleName(ValoTheme.BUTTON_ICON_ONLY);
			DashboardEventBus.register(this);
		}

		public void setUnreadCount(final int count) {
			setCaption(String.valueOf(count));

			String description = "Notifications";
			if (count > 0) {
				addStyleName(STYLE_UNREAD);
				description += " (" + count + " unread)";
			} else {
				removeStyleName(STYLE_UNREAD);
			}
			setDescription(description);
		}
	}

}
