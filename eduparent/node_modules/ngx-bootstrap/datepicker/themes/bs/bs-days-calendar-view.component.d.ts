import { EventEmitter } from '@angular/core';
import { BsDatepickerViewMode, BsNavigationDirection, BsNavigationEvent, CellHoverEvent, DatepickerRenderOptions, DaysCalendarViewModel, DayViewModel } from '../../models';
import { BsDatepickerConfig } from '../../bs-datepicker.config';
export declare class BsDaysCalendarViewComponent {
    private _config;
    calendar: DaysCalendarViewModel;
    options: DatepickerRenderOptions;
    onNavigate: EventEmitter<BsNavigationEvent>;
    onViewMode: EventEmitter<BsDatepickerViewMode>;
    onSelect: EventEmitter<DayViewModel>;
    onHover: EventEmitter<CellHoverEvent>;
    constructor(_config: BsDatepickerConfig);
    navigateTo(event: BsNavigationDirection): void;
    changeViewMode(event: BsDatepickerViewMode): void;
    selectDay(event: DayViewModel): void;
    hoverDay(cell: DayViewModel, isHovered: boolean): void;
}
