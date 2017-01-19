package org.brian.smith.adserver.repository;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.brian.smith.adserver.domain.Ad;
import org.springframework.stereotype.Component;

import com.brian.smith.adserver.exception.NotFoundException;
import com.google.common.base.Preconditions;

@Component
public class AdRepository {

	private Map<String, Ad> mapping = new HashMap<>();
	
	public Ad create(Ad ad) {
		Preconditions.checkNotNull(ad, "Ad object must be provided");
		Preconditions.checkArgument(StringUtils.isNotBlank(ad.getPartner_id()), "Ad partner_id must be provided");
		Preconditions.checkArgument(StringUtils.isNotBlank(ad.getAd_content()), "Ad content must be provided");
		Preconditions.checkArgument(ad.getDuration() > 0, "Ad duration must be greater than 0");		
		Ad existing = mapping.get(ad.getPartner_id());
		Date now = new Date();
		if (existing != null) {
			Preconditions.checkArgument(isAfter(existing.getExpires(), now), String.format("Partner %s already has an active ad campaign", ad.getPartner_id()));
		}		
		ad.setCreated(now);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, ad.getDuration());
		ad.setExpires(cal.getTime());
		mapping.put(ad.getPartner_id(), ad);
		return ad;
	}
	
	public Collection<Ad> getAll() {
		return mapping.values();	
	}
	
	public Ad getAd(String partnerId) {
		Ad ad = mapping.get(partnerId);
		if (ad == null) {
			throw new NotFoundException();
		}
		Preconditions.checkArgument(!isAfter(ad.getExpires(), new Date()), String.format("Partner %s does't have any active campaings", partnerId));		
		return ad;
	}
	
	public boolean isAfter(Date date, Date now) {
		return now.after(date);
	}
}
