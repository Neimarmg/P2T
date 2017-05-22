import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SsssComponent } from './ssss.component';

describe('SsssComponent', () => {
  let component: SsssComponent;
  let fixture: ComponentFixture<SsssComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SsssComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SsssComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
