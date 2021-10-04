package com.example.application.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

@PageTitle("Notification")
@Route(value = "notification", layout = MainLayout.class)
public class NotificationView extends Main {

	public NotificationView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Notification"));
		createNotifications(false);

		add(new H2("Notification (low contrast)"));
		createNotifications(true);
	}

	private void createNotifications(boolean lowContrast) {
		Button button = new Button("Info notification");
		button.addClassName("self-start");
		button.addClickListener(event -> createInfoNotification(lowContrast).open());
		button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		add(button);

		button = new Button("Success notification");
		button.addClassName("self-start");
		button.addClickListener(event -> createSuccessNotification(lowContrast).open());
		button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		add(button);

		button = new Button("Error notification");
		button.addClassName("self-start");
		button.addClickListener(event -> createErrorNotification(lowContrast).open());
		button.addThemeVariants(ButtonVariant.LUMO_ERROR);
		add(button);

		button = new Button("Warning notification");
		button.addClassName("self-start");
		button.addClickListener(event -> createWarningNotification(lowContrast).open());
		button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		add(button);
	}

	private Notification createInfoNotification(boolean lowContrast) {
		return createNotification(
				lowContrast,
				VaadinIcon.INFO_CIRCLE.create(),
				NotificationVariant.LUMO_PRIMARY.getVariantName()
		);
	}

	private Notification createSuccessNotification(boolean lowContrast) {
		return createNotification(
				lowContrast,
				VaadinIcon.CHECK_CIRCLE.create(),
				NotificationVariant.LUMO_SUCCESS.getVariantName()
		);
	}

	private Notification createErrorNotification(boolean lowContrast) {
		return createNotification(
				lowContrast,
				VaadinIcon.BAN.create(),
				NotificationVariant.LUMO_ERROR.getVariantName()
		);
	}

	private Notification createWarningNotification(boolean lowContrast) {
		return createNotification(
				lowContrast,
				VaadinIcon.EXCLAMATION_CIRCLE.create(),
				"warning"
		);
	}

	private Notification createNotification(boolean lowContrast, Icon icon, String themeName) {
		Notification notification = new Notification();
		notification.addThemeName(themeName);
		if (!lowContrast) {
			notification.getElement().getThemeList().add(Lumo.DARK);
		}

		icon.addClassNames("icon-s", "mt-m", "text-" + themeName);

		H3 title = new H3("Notification title");
		title.addClassNames("mb-0", "mt-m", "text-s");

		Span subtitle = new Span(new Text("Subtitle text goes here. "), new Anchor("#", "Example link"));
		subtitle.addClassNames("mb-m", "text-s");

		Span caption = new Span("00:00:00 AM");
		caption.addClassNames("mb-m", "mt-xs", "text-s");

		Div wrapper = new Div(title, subtitle, caption);
		wrapper.addClassNames("flex", "flex-col", "flex-grow");

		Button close = new Button(VaadinIcon.CLOSE_SMALL.create());
		close.addClickListener(e -> notification.close());
		close.addClassNames("m-0");

		Div content = new Div(icon, wrapper, close);
		content.addClassNames("flex", "gap-m", "ps-m");

		notification.add(content);
		return notification;
	}
}
