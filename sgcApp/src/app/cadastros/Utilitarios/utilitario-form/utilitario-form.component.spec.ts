import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UtilitarioFormComponent } from './utilitario-form.component';

describe('UtilitarioFormComponent', () => {
  let component: UtilitarioFormComponent;
  let fixture: ComponentFixture<UtilitarioFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UtilitarioFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UtilitarioFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
